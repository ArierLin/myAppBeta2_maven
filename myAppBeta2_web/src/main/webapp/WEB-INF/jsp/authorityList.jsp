<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/common.jsp"%>
<title>权限管理</title>
</head>
<body>
	<div class="div_table">
		<table cellpadding="0" cellspacing="0" class="outtab" align="center">
			<tbody>
				<tr>
					<td style="padding: 10px" align="center">
					<!-- 
					<fieldset class="setclass" align="center" width="98%">
						<legend class="legtab" width="85%">查询</legend>
						<table border="0" cellpadding="0" cellspacing="3" width="90%" align="center">
							<tbody>
								<tr>
									<td align="right">权限名称</td>
									<td align="left">
										<input id="name" value="${name}"/>
									</td>
									<td align="center">
										<div class="div_button" style="display:inline;">
											<img src="images/but_l.gif" align="absmiddle" border="0"></img>
											<input type="button" class="but" value="查询" onclick="searchAuthority(1);" ></input>
											<img src="images/but_r.gif" align="absmiddle" border="0"></img>
										</div>	
									</td>
								</tr>	
							</tbody>						
						</table>
						<br>				
					</fieldset>
					<br>
					<fieldset class="setclass" align="center" width="98%">
						<legend class="legtab" width="85%">操作</legend>
						<table cellpadding="3"  border="0" cellspacing="1" width="98%" id="operate">
							<tbody>
								<tr>			
									<td align="center">
										<div class="div_button">
											<img src="images/but_l.gif" align="absmiddle" border="0"></img>
											<input type="button" class="but" value="增加权限信息" onclick="goToUrl('role/toAddOrUpdateAuthorityPage')"></input>
											<img src="images/but_r.gif" align="absmiddle" border="0"></img>
										</div>	
									</td>
									<td align="center">
										<div class="div_button">
											<img src="images/but_l.gif" align="absmiddle" border="0"></img>
											<input type="button" class="but" value="修改权限信息" onclick="toUpdateOneAuthorityPage()"></input>
											<img src="images/but_r.gif" align="absmiddle" border="0"></img>
										</div>	
									</td>
									<td align="center">
										<div class="div_button">
											<img src="images/but_l.gif" align="absmiddle" border="0"></img>
											<input type="button" class="but" value="删除权限信息" onclick="multiDeleteAuthority();"></input>
											<img src="images/but_r.gif" align="absmiddle" border="0"></img>
										</div>	
									</td>
								</tr>
							</tbody>
						</table>
						</br>
					</fieldset>
					 -->
					<!-- 列表区 -->
					<fieldset class="setclass" align="center" width="98%">
						<legend class="legtab" width="85%">系统权限列表</legend>
						<table cellpadding="3" class="normaltab" border="0" cellspacing="1" width="98%" >
							<tbody>	
								<tr class="tbtitle" >
									<!-- 权限id -->
									<td width="15%">权限id</td>
									<!-- 权限名称 -->
									<td width="15%">权限名称</td>
									<!-- 权限地址 -->
									<td width="15%">权限地址</td>
									<!-- 权限描述 -->
									<td width="15%">权限描述</td>
									<!-- 权限创建时间 -->
									<td width="15%">权限创建时间</td>
								</tr>
								<c:if test="${authorities != null }">
									<c:forEach items="${authorities }" var="authority" varStatus="items">
										<tr class="normaltab_tr" >
											<td align="center">${authority.id}</td>
											<td align="center">${authority.name}</td>
											<td align="center">${authority.url}</td>
											<td align="center">${authority.desc}</td>
											<td align="center">${authority.createTime}</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</fieldset>
					</td>	
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>