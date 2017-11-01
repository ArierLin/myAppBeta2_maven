<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}/js/user.js"></script>
<title>用户管理</title>
</head>
<body>
	<div class="div_table">
		<table cellpadding="0" cellspacing="0" class="outtab" align="center">
			<tbody>
				<tr>
					<td style="padding: 10px" align="center">
					<fieldset class="setclass" align="center" width="98%">
						<legend class="legtab" width="85%">查询</legend>
						<table border="0" cellpadding="0" cellspacing="3" width="90%" align="center">
							<tbody>
								<tr>
									<!-- 查询条件 -->
									<td align="right">用户名称</td>
									<td align="left">
										<input id="name" value="${name}"/>
									</td>
									<td align="center">
										<div class="div_button" style="display:inline;">
											<img src="images/but_l.gif" align="absmiddle" border="0"></img>
											<input type="button" class="but" value="查询" onclick="searchUser(1);" ></input>
											<img src="images/but_r.gif" align="absmiddle" border="0"></img>
										</div>	
									</td>
								</tr>	
							</tbody>						
						</table>
						<br>				
					</fieldset>
					<br>
					<!-- 操作区 -->
					<fieldset class="setclass" align="center" width="98%">
						<!-- 操作 -->
						<legend class="legtab" width="85%">操作</legend>
						<table cellpadding="3"  border="0" cellspacing="1" width="98%" id="operate">
							<tbody>
								<tr>			
									<td align="center">
										<div class="div_button">
											<img src="images/but_l.gif" align="absmiddle" border="0"></img>
											<input type="button" class="but" value="增加用户信息" onclick="goToUrl('user/toAddUserPage')"></input>
											<img src="images/but_r.gif" align="absmiddle" border="0"></img>
										</div>	
									</td>
									<!-- 编辑按钮 -->
									<td align="center">
										<div class="div_button">
											<img src="images/but_l.gif" align="absmiddle" border="0"></img>
											<input type="button" class="but" value="修改用户信息" onclick="toUpdateOneUserPage()"></input>
											<img src="images/but_r.gif" align="absmiddle" border="0"></img>
										</div>	
									</td>
									<!-- 删除按钮 -->
									<td align="center">
										<div class="div_button">
											<img src="images/but_l.gif" align="absmiddle" border="0"></img>
											<input type="button" class="but" value="删除用户信息" onclick="multiDeleteUser();"></input>
											<img src="images/but_r.gif" align="absmiddle" border="0"></img>
										</div>	
									</td>
								</tr>
							</tbody>
						</table>
						</br>
					</fieldset>
					<!-- 列表区 -->
					<fieldset class="setclass" align="center" width="98%">
						<legend class="legtab" width="85%">系统用户列表</legend>
						<table cellpadding="3" class="normaltab" border="0" cellspacing="1" width="98%" >
							<tbody>	
								<tr class="tbtitle" >
									<td width="15%">
										<input type="checkbox" id="checkAll" onclick="checkAllOrNot(this);">全选</input>
									</td>
									<!-- 用户id -->
									<td width="15%">用户id</td>
									<!-- 用户名称 -->
									<td width="15%">用户名称</td>
									<!-- 用户状态 -->
									<td width="15%">用户状态</td>
									<!-- 用户创建时间 -->
									<td width="15%">用户创建时间</td>
									<!-- 用户修改时间 -->
									<td width="15%">用户修改时间</td>
								</tr>
								<c:if test="${users != null }">
									<c:forEach items="${users }" var="user" varStatus="items">
										<tr class="normaltab_tr" onclick="changeTrState(this)">
											<td align="center">
												<input type="checkbox" name="checkOne" value="${user.id}" onclick="checkOne(this);"></input>
											</td>
											<td align="center">${user.id}</td>
											<td align="center">${user.userName}</td>
											<td align="center">${user.isDel}</td>
											<td align="center">${user.createTime}</td>
											<td align="center">${user.updateTime}</td>
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