<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/common.jsp"%>
<script type="text/javascript">
	$('#myTab a').click(function(e) {
		e.preventDefault()
		$(this).tab('show')
	})
</script>
<script type="text/javascript" src="${basePath}/js/webService.js"></script>
<div class="div_table" style="height:500px;">
	<ul id="myTab" class="nav nav-tabs">
		<li class="active"><a href="#home" data-toggle="tab">暂未实现</a></li>
		<li><a href="#ios" data-toggle="tab">暂未实现</a></li>
		<li class="dropdown">
			<a href="#" id="myTabDrop1" class="dropdown-toggle" data-toggle="dropdown">天气查询
				<b class="caret"></b>
			</a>
			<ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop1">
				<li><a href="#cityWeather" tabindex="-1" data-toggle="tab">城市天气查询</a></li>
				<li><a href="#no" tabindex="-1" data-toggle="tab">暂未实现</a></li>
			</ul>
		</li>
	</ul>
	<div id="myTabContent" class="tab-content">
		<div class="tab-pane fade in active" id="home">
			<p>该功能暂未实现！敬请期待！</p>
		</div>
		<div class="tab-pane fade" id="ios">
			<p>该功能暂未实现！敬请期待!</p>
		</div>
		<div class="tab-pane fade" id="cityWeather" >
			<h3>查询城市天气</h3>
			<p>天气预报服务数据来源于中国气象局http://www.cma.gov.cn/
			数据每2.5小时左右自动更新一次，准确可靠。包括 340 多个中国主要城市和 60 多个国外主要城市三日内的天气预报数据。</p>
			<form class="form-inline" role="form">
				<div class="form-group">
					<label class="sr-only" for="name">城市名称</label>
					<input type="text" class="form-control" id="name" 
			         placeholder="请输入城市名称">
					<button id="cityWeatherSearchBtn" class="btn btn-default">查询</button>
				</div>
			</form>
			<div id="cityWeatherSearchResult" style="overflow: auto;">
				<h4>查询结果</h4>
				<h3>当日天气状况</h3>
				<table class="table"  style="table-layout:fixed;">
					<tbody id="resultBody">
				    
					</tbody>
				</table>
				<h3>未来两天天气</h3>
				<table class="table"  style="table-layout:fixed;">
					<tbody id="resultBodyPart2">
			    
					</tbody>
				</table>
				<h3>城市或地区的介绍</h3>
				<table class="table"  style="table-layout:fixed;">
					<tbody id="resultBodyPart3">
			    
					</tbody>
				</table>
			</div>
		</div>
		<div class="tab-pane fade" id="no">
			<p>该功能暂未实现！敬请期待!</p>
		</div>
	</div>

</div>