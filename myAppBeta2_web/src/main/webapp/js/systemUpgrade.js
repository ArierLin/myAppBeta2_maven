var UploadToComplete="上传完成";
var preprocessingRemind="请等待文件预处理完毕，再点击上传按钮";
var uploaded="已经上传成功，请点击下一步";
var reUploaded="已经上传成功，是否重新上传?";
var breakpointContinuingly="是否断点续传?";
var dataSizeError="获取数据大小错误";
var tarRemind ="仅限上传以.tar.gz结尾的文件";
var networkAnomalies="网络异常,请重新上传";	
var httpErrorRemind="客户端状态错误，错误码，请刷新页面重试. HTTP Status Code 为";
var httpErrorRemind2="服务器错误，请重新登陆，重试. 错误码 HTTP Status Code 为";
var httpErrorRemind3="请重新登陆，重试. HTTP Status Code 为";
var clickUpload="请点击上传!";
var fileRemind = "请选择文件";// 请选择文件

var flag = 0; // 上传成功标识 1表示长传成功
var newfilename; // 改名后的文件名
var eachSize = 512 * 1024; // 片段长度
var size = 0; // 文件总长度
var start = 0; // 返回的文件的长度
var timer = null; // 定时器对象
var fileObj; // 文件对象
var fileName; // 经过md5加密后的文件名（包括后缀）
var xhr;

//选择文件，在input中显示文件名称，显示、更新预处理进度条，加密文件
function prepareFile(){

	$("#md5FileName").val("");//更换文件，需将md5先清空，避免md5码错误
	fileObj = document.getElementById("file").files[0]; // js 获取文件对象     
	//调用MD5js加密文件
	selectFile(fileObj);
	//显示文件名
	var fileCatetorys = $("#file").val().split("\\");
	$("#fileName").val("");
	$("#fileName").val(fileCatetorys[fileCatetorys.length-1]);
}

//点击上传，准备开始真正上传文件
function upladFile(){
	console.log("----upladFile上传文件----------------");
	if(!$("#file").val()){
		return alert("请选择文件");
	}
	if(!$("#md5FileName").val()){
		return alert("请等待文件预处理完毕，再点击上传按钮");
	}
	fileObj = document.getElementById("file").files[0]; // js 获取文件对象     
    size = fileObj.size; 
    fileName=$("#md5FileName").val()+".tar.gz";
    
    //查询已上传文件大小，若还没有上传则返回0
     $.ajax({
		url:"system/getFileResumePoint",
		type:"post",
		dataType:"json",
		async:false,
		data:{
			fileName:fileName
		},
		success:function(data){
			console.log("----文件已上传大小data="+data+ "，文件总大小size="+size);
			//已经上传完文件
			if(data==size){
				alert("已经上传成功，请点击下一步");
				start = size;
				//填满上传进度
				$("#progressbar").css("float","left");
				$("#progressbar").css("width",data/size*200);
				//显示上传进度100%
				var percentageDiv = document.getElementById("percentage");
				percentageDiv.innerHTML = Math.round(data/size * 100)+"%";
				flag=1;//等于1表示文件上传完成，可以点击下一步
				//"已经上传成功，是否重新上传?"
				/*
				layer.confirm(reUploaded,{
				btn:["是","否"]
				},function(){
					//是
					start=0;
					upladFileFromBreakPoint() ;
				},function(){
					//否
					start=size;
					$("#progressbar").css("float","left");
					$("#progressbar").css("width",data/size*200);
					var percentageDiv = document.getElementById("percentage");
					percentageDiv.innerHTML = Math.round(data/size * 100)+"%";
				});*/
			//已经上传过一部分文件	
			}else if(data >0 ){
				start = data;
				upladFileFromBreakPoint() ;
				//"是否断点续传?"
				/*layer.confirm(breakpointContinuingly,{
				btn:["是","否"]
				},function(){
					//是,断点续传
					start = data;
					upladFileFromBreakPoint() ;
				},function(){
					//否,从头开始传
					start = 0;
					upladFileFromBreakPoint() ;
				});*/
			}else{//还没有上传过文件,从头开始传
				start=0;
				upladFileFromBreakPoint() ;
			}
		},
		error:function(){
			alert("获取数据大小错误");
		}
	});
}

//断点续传
function upladFileFromBreakPoint() { 

	xhr = null;
	
 	if($("#file").val().indexOf(".tar.gz")<=0){
    	return alert("仅限上传以.tar.gz结尾的文件");
    }
    fileObj = document.getElementById("file").files[0]; // js 获取文件对象     
    size = fileObj.size; 
    fileName = $("#md5FileName").val()+".tar.gz";
    /* if(timer == null){
    	timer = setInterval("getBreakpoint('"+fileName+"')",500);
    }*/
    var progressBar = document.getElementById("progressBar");  
	var percentageDiv = document.getElementById("percentage");
	//获取文件的断点位置
	start = getBreakpoint($("#md5FileName").val()+".tar.gz");
	
	//文件还没上传完成
	if(start<size){
		console.log("----文件开始上传位置start="+start+ "，文件总大小size="+size);	
		$(progressBar).val(start);
		
		var FileController = "system/progress"; // 接收上传文件的后台地址
		// FormData 对象
		var form = new FormData();
		xhr = new XMLHttpRequest();
		form.append("file", fileObj.slice(start, start + eachSize)); // 分割文件对象
		form.append("fileName", fileName); // 文件名称（这个不用传）

		// XMLHttpRequest 对象
		xhr.open("post", FileController, true);
		xhr.setRequestHeader("context-type", "text/xml;charset=GB2312");
		
		xhr.addEventListener("error", function() {
			clearInterval(timer);
			alert("网络异常,请重新上传");
		}, false);

		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				if (xhr.status == 200) {
					// 得到返回值
					var b = xhr.responseText;
					if (b == "true") {
						// 上传一个片段成功，继续下个片段的上传。继续调用断点续传的方法
//						start += eachSize;
						upladFileFromBreakPoint();
					} else {
						alert("网络异常,请重新上传" + "   " + b);
					}
				} else if (xhr.status > 399 && xhr.status < 420) {
					alert(httpErrorRemind + xhr.status);
				} else if (xhr.status > 499 && xhr.status < 520) {
					alert(httpErrorRemind2 + xhr.status);
				} else if (xhr.status > 200 && xhr.status < 399) {
					alert(httpErrorRemind3 + xhr.status);
				}
			}
		};
		xhr.send(form);//发送请求
	} else {
		// alert("已经上传过了,结束上传！");
	}
}  

// 查询文件断点
function getBreakpoint(fileName){
	fileName=$("#md5FileName").val()+".tar.gz";
    $.ajax({
		url:"system/getFileResumePoint",
		type:"post",
		dataType:"json",
		async:false,
		data:{
			fileName:fileName
		},
		success:function(data){
			console.log("----文件断点位置（已上传大小data）="+data+ "，文件总大小size="+size);
			start = data;
			
			if(data==size){
				clearInterval(timer);
				alert("getBreakpoint上传完成");
				newfilename = fileName;
				flag=1;
			}
			//进度条
			$("#progressbar").css("float","left");
			$("#progressbar").css("width",data/size*200);
			//进度百分比
			var percentageDiv = document.getElementById("percentage");
			percentageDiv.innerHTML = Math.round(data/size * 100)+"%";
		},
		error:function(){
		}
	});
	return start;
}

	
//下一步
function addfileOver() {  

	 if (flag == 1) {
		var oDiv = document.getElementById('input11'); // 获取元素div
		oDiv.onclick = function() { // 给元素增加点击事件
			$.ajax({
				type : "post",
				url : "UpgradeManagement/AddUpgrade",
				data : "fileUploadName=" + fileName,
				success : function(data, textStatus) {
					$(".right").html("");
					$(".right").html(data);
					$(window).resize();// 重新调整页面宽度
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
				}
			});
		};
		oDiv.click();
	} else {
		if ($("#file").val()) {
			return alert("请点击上传!");
		}
		alert("请选择文件");
	} 
}




