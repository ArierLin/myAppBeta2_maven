//查询城市天气预报
$("#cityWeatherSearchBtn").click(function(e){
	e.preventDefault()
	var cityName = $("#name").val();
	$.ajax({
		url:"webService/queryCityWeather",
		type:"post",
		data:{cityName:cityName},
		success:function(e){
			$("#resultBody").html("");
			$("#resultBodyPart2").html("");
			$("#resultBodyPart3").html("");
			var firstPart = e[0];var nextPart = e[1];var lastPart = e[2];
			for(var key in firstPart){
				var tr = $("<tr class='success'></tr>");
				var td_1 = $("<td >"+key+"</td>");
				var td_2 = $("<td width='70%;' style='white-space:normal;'>"+firstPart[key]+"</td>");
				tr.append(td_1).append(td_2);
				$("#resultBody").append(tr);
			} 
			for(var key in nextPart){
				var tr = $("<tr class='success'></tr>");
				var td_1 = $("<td >"+key+"</td>");
				var td_2 = $("<td width='70%;' style='white-space:normal;'>"+nextPart[key]+"</td>");
				tr.append(td_1).append(td_2);
				$("#resultBodyPart2").append(tr);
			} 
			for(var key in lastPart){
				var tr = $("<tr class='success'></tr>");
				var td_1 = $("<td >"+key+"</td>");
				var td_2 = $("<td width='70%;' style='white-space:normal;'>"+lastPart[key]+"</td>");
				tr.append(td_1).append(td_2);
				$("#resultBodyPart3").append(tr);
			} 
		},
		error:function(){}
	});
	
});