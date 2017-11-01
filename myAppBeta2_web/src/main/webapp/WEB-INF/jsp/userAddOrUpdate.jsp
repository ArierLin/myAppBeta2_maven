<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}/js/user.js"></script>
<script type="text/javascript">

	//下面的正则表达式建议各位收藏哦，项目上有可能会用得着
	$(function(){  
		
		$("#name").blur(function(){
			validUser();
			}
		);
		
		//$('#password').blur(function(e) {
		$('#password').keyup(function(e) {
			$('#passEqual').html('');
			
			//密码为八位及以上并且字母数字特殊字符三项都包括
			var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
			//密码为七位及以上并且字母、数字、特殊字符三项中有两项，强度是中等 
			var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
			var enoughRegex = new RegExp("(?=.{6,}).*", "g");
			//如果长度不够6个，则提示长度不够
			if (false == enoughRegex.test($(this).val())) {
				$('#passstrength').html('长度不够');
				return false;
			} else if (strongRegex.test($(this).val())) {
				$('#passstrength').className = 'ok';
				$('#passstrength').html('强度高!');
			} else if (mediumRegex.test($(this).val())) {
				$('#passstrength').className = 'alert';
				$('#passstrength').html('强度中!');
			} else {
				$('#passstrength').className = 'error';
				$('#passstrength').html('强度弱!');
			}
			return true;
		});
		//验证两次输入的密码是否一致
		$("#secondPass").blur(function(){
			var password = $("#password").val();
			var secondPass = $("#secondPass").val();
			if(password==null||password==undefined||password.trim()==""||password.trim().length<6){
				$('#passstrength').html('密码不符合要求!密码不能少于6位。');
				return false;
			}
			if(password!=secondPass){
				$('#passEqual').html('两次密码输入不一致，请重新确认。');
				return false;
			}else{
				$('#passEqual').html('密码一致！');
			}
		});
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
										<c:when test="${user.id != null}">
											<!-- 修改用户 -->
											<b class="14font">修改用户</b>
										</c:when>
										<c:otherwise>
											<!-- 添加用户 -->
											<b class="14font">添加用户</b>
										</c:otherwise>
									</c:choose>
								</td>
								<td width="200" align="right" style="padding-right: 4px">&nbsp;</td>
							</tr>
							<tr>
								<td align="center" colspan="2"><font color="red" id="errorMsg"></font>
									<input type="hidden" id="userName" value="${user.userName }"/>
								</td>
							</tr>
						</tbody>	
					</table>
					<br>
					<fieldset class="setclass" align="center" width="98%">
						<!-- 用户信息 -->
						<legend class="legtab" width="85%">用户信息</legend>
						<table border="0" cellpadding="0" cellspacing="3" width="80%" align="left" >
							<tbody>
								<tr>
									<!-- 用户名称 -->
									<td align="right" width="50%">用户名称:</td>
									<td align="left">	
										<input type="text" id="name" value="${user.userName }" ></input>
										<span id="validUserName"></span>
									</td>
								</tr>
								<c:choose>
									<c:when test="${user.id != null}">
										<tr>
											<!-- 用户密码 -->
											<td align="right" width="50%">用户密码:</td>
											<td align="left">	
												<input type="password" id="password" value="${user.password }" readonly></input>
											</td>
										</tr>
									</c:when>
									<c:otherwise>
										<tr>
											<!-- 用户密码 -->
											<td align="right" width="50%">用户密码:</td>
											<td align="left">	
												<input type="password" id="password" value="${user.password }" ></input>
												<span id="passstrength"></span>
											</td>
										</tr>
										<tr>
											<!-- 确认密码 -->
											<td align="right" width="50%">确认密码:</td>
											<td align="left">	
												<input type="password" id="secondPass" value="" ></input>
												<span id="passEqual"></span>
											</td>
										</tr>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
						<table border="0" cellpadding="0" cellspacing="3" width="100%" align="center">
							<tbody>
								<tr>
									<td align="center">
										<div class="div_button" style="margin-left:50%;width:auto;float:left;">
											<img src="images/but_l.gif" align="absmiddle" border="0"></img>
												<c:choose>
													<c:when test="${user.id != null}">
														<input type="button" class="but" value="保存" onclick="updateUser('${user.id }');"></input>
													</c:when>
													<c:otherwise>
														<input type="button" class="but" value="保存" onclick="saveUser();"></input>
													</c:otherwise>
												</c:choose>
											<img src="images/but_r.gif" align="absmiddle" border="0"></img>
										</div>	
									</td>
									<td align="center">
										<div class="div_button" style="margin-left:50%;width:auto;float:left;">
											<img src="images/but_l.gif" align="absmiddle" border="0"></img>
												<input type="button" class="but" value="返回" onclick="goToUrl('user/toUserManagementPage')"></input>
											<img src="images/but_r.gif" align="absmiddle" border="0"></img>
										</div>	
									</td>
								</tr>	
							</tbody>						
						</table>
					</fieldset>
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
									<!-- 角色描述 -->
									<td width="15%">角色描述</td>
									<!-- 角色类型 -->
									<td width="15%">角色类型</td>
									<!-- 角色创建时间 -->
									<td width="15%">角色创建时间</td>
									<!-- 角色修改时间 -->
									<td width="15%">角色修改时间</td>
								</tr>
							</tbody>
						</table>
						<div style="height:275px;overflow-y:scroll;overflow-x:hidden;width:98%;">
							<table cellpadding="3" class="normaltab" border="0" cellspacing="1" width="98%" >
								<tbody>	
									<c:if test="${roles != null }">
										<c:forEach items="${roles }" var="role" varStatus="items">
											<tr class="normaltab_tr" onclick="changeTrState(this)">
												<td align="center">
													<input type="checkbox" name="checkOne" value="${role.id}" onclick="checkOne(this);"
														<c:if test="${selectedRoles != null }">
															<c:forEach items="${selectedRoles }" var="selectedRole" >
																<c:if test="${selectedRole.id == role.id}">
																	checked = "checked";
																</c:if>
															</c:forEach>
														</c:if>
													></input>
												</td>
												<td align="center">${role.id}</td>
												<td align="center">${role.name}</td>
												<td align="center">${role.desc}</td>
												<td align="center">${role.type}</td>
												<td align="center">${role.createTime}</td>
												<td align="center">${role.updateTime}</td>
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