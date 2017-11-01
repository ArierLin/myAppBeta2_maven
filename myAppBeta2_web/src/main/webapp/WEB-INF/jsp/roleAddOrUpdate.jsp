<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}/js/role.js"></script>
<script type="text/javascript">
	$(function(){
		
		
	});
</script>
<div class="div_table">
	<table cellpadding="0" cellspacing="0" class="outtab" align="center">
		<tbody>
			<tr>
				<td style="padding: 10px" align="center">
					<table cellpadding="0" cellspacing="0" width="100%">
						<tbody>
							<tr>
								<td>
									<c:choose>
										<c:when test="${role.id != null}">
											<!-- 修改角色 -->
											<b class="14font">修改角色</b>
										</c:when>
										<c:otherwise>
											<!-- 添加角色 -->
											<b class="14font">添加角色</b>
										</c:otherwise>
									</c:choose>
								</td>
								<td width="200" align="right" style="padding-right: 4px">&nbsp;</td>
							</tr>
							<tr>
								<td align="center" colspan="2"><font color="red" id="errorMsg"></font></td>
							</tr>
						</tbody>	
					</table>
					<br>
					<fieldset class="setclass" align="center" width="98%">
						<!-- 角色信息 -->
						<legend class="legtab" width="85%">角色信息</legend>
						<table border="0" cellpadding="0" cellspacing="3" width="80%" align="left" >
							<tbody>
								<tr>
									<!-- 角色名称 -->
									<td align="right" width="80px">角色名称:</td>
									<td align="left">	
										<input type="text" id="name" value="${role.name }" ></input>
									</td>
								</tr>
								<tr>
									<!-- 角色类型 -->
									<td align="right" width="50%" >
										<span style="color:red;">*</span>
										角色类型:
									</td>
									<td align="left" width="100px">
										<select id="type" >
											<option value="0" <c:if test="${role.type==0}">selected=selected</c:if>>普通用户</option>
											<option value="1" <c:if test="${role.type==1}">selected=selected</c:if>>高级用户</option>
											<option value="2" <c:if test="${role.type==2}">selected=selected</c:if>>超级用户</option>
										</select>
									</td>
								</tr>
								<tr>
									<!-- 角色描述 -->
									<td align="right" width="80px">角色描述:</td>
									<td align="left">	
										<textarea id="desc" >${role.desc }</textarea>
									</td>
								</tr>
							</tbody>
						</table>
						<table border="0" cellpadding="0" cellspacing="3" width="100%" align="center">
							<tbody>
								<tr>
									<td align="center">
										<div class="div_button" style="margin-left:50%;width:auto;float:left;">
											<img src="images/but_l.gif" align="absmiddle" border="0"></img>
												<c:choose>
													<c:when test="${role.id != null}">
														<input type="button" class="but" value="保存" onclick="updateRole('${role.id }');"></input>
													</c:when>
													<c:otherwise>
														<input type="button" class="but" value="保存" onclick="saveRole();"></input>
													</c:otherwise>
												</c:choose>
											<img src="images/but_r.gif" align="absmiddle" border="0"></img>
										</div>	
									</td>
									<td align="center">
										<div class="div_button" style="margin-left:50%;width:auto;float:left;">
											<img src="images/but_l.gif" align="absmiddle" border="0"></img>
												<input type="button" class="but" value="返回" onclick="goToUrl('role/toRoleManagementPage')"></input>
											<img src="images/but_r.gif" align="absmiddle" border="0"></img>
										</div>	
									</td>
								</tr>	
							</tbody>						
						</table>
					</fieldset>
					<fieldset class="setclass" align="center" width="98%">
						<legend class="legtab" width="85%">系统权限列表</legend>
						<table cellpadding="3" class="normaltab" border="0" cellspacing="1" width="98%" >
							<tbody>	
								<tr class="tbtitle" >
									<td width="15%">
										<input type="checkbox" id="checkAll" onclick="checkAllOrNot(this);">全选</input>
									</td>
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
							</tbody>
						</table>
						<div style="height:275px;overflow-y:scroll;overflow-x:hidden;width:98%;">
							<table cellpadding="3" class="normaltab" border="0" cellspacing="1" width="98%" >
								<tbody>	
									<c:if test="${authorities != null }">
										<c:forEach items="${authorities }" var="authority" varStatus="items">
											<tr class="normaltab_tr" onclick="changeTrState(this)">
												<td align="center">
													<input type="checkbox" name="checkOne" value="${authority.id}" onclick="checkOne(this);"
														<c:if test="${selectedAuthorities != null }">
															<c:forEach items="${selectedAuthorities }" var="selectAuthority" >
																<c:if test="${selectAuthority.id == authority.id}">
																	checked = "checked";
																</c:if>
															</c:forEach>
														</c:if>
													></input>
												</td>
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
						</div>
					</fieldset>
				</td>	
			</tr>
		</tbody>
	</table>
</div>