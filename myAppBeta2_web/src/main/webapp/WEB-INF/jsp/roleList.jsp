<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}/js/role.js"></script>
<title>角色管理</title>
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
									<td align="right">角色名称</td>
									<td align="left">
										<input id="name" value="${name}"/>
									</td>
									<td align="right">角色类型</td>
									<td align="left">
										<input id="type" value="${type}"/>
									</td>
									<td align="right">角色创建时间</td>
									<td align="left">
										<input id="createTime" value="${createTime}"/>
									</td>
									<td align="center">
										<div class="div_button" style="display:inline;">
											<img src="images/but_l.gif" align="absmiddle" border="0"></img>
											<input type="button" class="but" value="查询" onclick="searchRole(1);" ></input>
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
											<input type="button" class="but" value="增加角色信息" onclick="goToUrl('role/toAddRolePage')"></input>
											<img src="images/but_r.gif" align="absmiddle" border="0"></img>
										</div>	
									</td>
									<!-- 编辑按钮 -->
									<td align="center">
										<div class="div_button">
											<img src="images/but_l.gif" align="absmiddle" border="0"></img>
											<input type="button" class="but" value="修改角色信息" onclick="toUpdateOneRolePage()"></input>
											<img src="images/but_r.gif" align="absmiddle" border="0"></img>
										</div>	
									</td>
									<!-- 删除按钮 -->
									<td align="center">
										<div class="div_button">
											<img src="images/but_l.gif" align="absmiddle" border="0"></img>
											<input type="button" class="but" value="删除角色信息" onclick="multiDeleteRole();"></input>
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
						<legend class="legtab" width="85%">系统角色列表</legend>
						<table cellpadding="3" class="normaltab" border="0" cellspacing="1" width="98%" >
							<tbody>	
								<tr class="tbtitle" >
									<td width="15%">
										<input type="checkbox" id="checkAll" onclick="checkAllOrNot(this);">全选</input>
									</td>
									<!-- 角色id -->
									<td width="15%">角色id</td>
									<!-- 角色名称 -->
									<td width="15%">角色名称</td>
									<!-- 角色类型 -->
									<td width="15%">角色类型</td>
									<!-- 角色描述 -->
									<td width="15%">角色描述</td>
									<!-- 角色创建时间 -->
									<td width="15%">角色创建时间</td>
								</tr>
								<c:if test="${roles != null }">
									<c:forEach items="${roles }" var="role" varStatus="items">
										<tr class="normaltab_tr" onclick="changeTrState(this)">
											<td align="center">
												<input type="checkbox" name="checkOne" value="${role.id}" onclick="checkOne(this);"></input>
											</td>
											<td align="center">${role.id}</td>
											<td align="center">${role.name}</td>
											<td align="center">${role.type}</td>
											<td align="center">${role.desc}</td>
											<td align="center">${role.createTime}</td>
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