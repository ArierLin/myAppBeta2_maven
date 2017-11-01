<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统升级管理页面</title>
<%@include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}/js/md5.js"></script>
<script type="text/javascript" src="${basePath}/js/html5hash.js"></script>
<script type="text/javascript" src="${basePath}/js/systemUpgrade.js"></script>
<div class="div_table">
	<table cellpadding="0" cellspacing="0" class="outtab" align="center">
		<tbody>
			<tr>
				<td style="padding: 10px" align="center">
					<table cellpadding="0" cellspacing="0" width="100%">
						<tbody>
							<tr>
								<td><b class="14font">添加升级<h3>系统升级管理页面(支持升级文件断点续传)</h3></b></td>
							</tr>
							<tr>
								<td align="center" colspan="2">
									<font color="red" id="errorMsg"></font>
								</td>
							</tr>
						</tbody>
					</table> <br>
					<fieldset class="setclass" align="center" width="98%">
						<legend class="legtab" width="85%">添加升级文件第一步，上传升级文件</legend>
						<div>
							<span style="float: left; margin-left: 3px;">
								<font style="color: red; font-size: 20px;">*仅限上传以.tar.gz结尾的文件</font>
							</span>
						</div>
						<div style="margin-left: 10px; width: 98.5%; clear: both;">
							<!-- 显示文件名称 -->
							<input type="text" style="margin-left: 16px; height: 23px; width: 130px; align: center; clear: both;"id="fileName"></input>
							<!-- 选择文件 -->
							<input type="button" class="btn" value="选择文件" onclick="document.getElementById('file').click();" />
							<!-- 上传 -->
							<input type="button" id="upladeButton" value="上传" onclick="upladFile()"></input>
						</div>
						<!-- 文件预处理 -->
						<div class="modal-header" style="margin-top: 2px; margin-bottom: 2px; clear: both">
							<h4 class="modal-title" id="myModalLabel">文件预处理</h4>
						</div>
						<!-- 文件预处理进度条 -->
						<div style="float: left; margin-left: 430px; background-color: white; border: solid 1px grey; width: 200px; height: 20px;">
							<div id="readyProgressbar" style="align: left; background-color: blue; width: 0px; height: 20px;">
							</div>
						</div>
						<div style="width: 20px; align: center; float: left;">
							<span id="readyPercentage" style="color: blue; font-size: 16px;"></span>
						</div>
						<!-- 文件上传进度 -->
						<div class="modal-header" style="margin-top: 2px; margin-bottom: 2px; clear: both">
							<h4 class="modal-title" id="myModalLabel">文件上传进度</h4>
						</div>
						<div style="float: left; margin-left: 430px; background-color: white; border: solid 1px grey; width: 200px; height: 20px;">
							<div id="progressbar" style="align: left; background-color: blue; width: 0px; height: 20px;">
							</div>
						</div>
						<!-- 文件上传进度百分比显示 -->
						<div style="width: 20px; align: center; float: left;">
							<span id="percentage" style="color: blue; font-size: 16px;"></span>
						</div>
						<div style="align: center; float: left; margin-left: 500px; margin-top: 2px; margin-bottom: 2px;">
							<!-- 文件域 -->
							<input type="file" name="file" class="file" id="file" size="28" style="display: none" data-options="required:true" onchange="prepareFile();" />
							<!-- 加密过的文件名 -->
							<input style="display: none" type="text" id="md5FileName"></input>
							<input type="button" value="返回" onclick="goToUrl('UpgradeManagement/FileUpgrade')"></input>
							<input type="button" value="下一步" id="input11" onclick="addfileOver()"></input>
						</div>
					</fieldset>
				</td>
			</tr>
		</tbody>
	</table>
</div>
