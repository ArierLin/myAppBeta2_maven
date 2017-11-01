<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="head" style="height: 143px; width: 100%;">
	<table cellpadding="0" cellspacing="0" border="0" width="100%" id="topmenu" class="menuclass">
		<tbody>
			<tr>
				<td align="center">
					<img src="images/header.png" width="100%" vspace="0" border="0" height="113px">
				</td>
			</tr>
		</tbody>	
	</table>
	<table cellpadding="0" cellspacing="0" border="0" width="100%" id="topmenu" class="menuclass">
		<tbody>
			<tr>
				<td width="8" align="center">
					<img src="images/dot.gif">
				</td>
				<td tabindex="1" width="20" align="center" onclick="changeFrame();">
					<nobr>
						<!--切换菜单-->
						<img id="tree_ctrl" name="frame_ctrl" src="images/close.gif" align="absmiddle" alt="切换菜单">
					</nobr>
				</td>
				<td width="*" valign="top">
					<!--window style-->
					<table cellspacing="1" cellpadding="0" border="0" width="100%" id="rootMenu" name="rootMenu" height="24">
						<!--window style end -->
						<tbody>
							<tr height="24">
								<td tabindex="1" width="60" align="center"></td>
								<td tabindex="1" width="60" align="center"></td>
								<td tabindex="1" width="60" align="center"></td>
								<td tabindex="1" width="60" align="center"></td>
								<td disabled="" width="*">
									<marquee id="marqueeContent" width=100%; scrollamount=5>
										<FONT face=楷体_GB2312 color=#ff0000 size=3>
										<STRONG>欢迎使用本系统！
										</STRONG>
										</FONT>
									</marquee>
								</td>
							</tr>
						</tbody>
					</table>
				</td>
				<!--用户-->
				<td align="right" width="1"><img src="images/top_bg_l.gif" height="24"></td>
				<td align="left" class="coolButton" width="60" tabindex="1">
					<nobr>
						<img src="images/userinfo.gif" height="16" width="19" align="absmiddle">用户:xxx
					</nobr>
				</td>
				<!--修改密码-->
				<td class="coolButton" tabindex="1" width="60" align="center" onclick="goToUrl('system/passwordChange')">
					<nobr>
						<img src="images/totop.gif" height="21" width="20" align="absmiddle" border="0" style="margin-top: 4px;">
						<span id="listid">修改密码</span>
					</nobr>
				</td>
				<!--退出系统-->
				<td class="coolButton" tabindex="1" width="88" align="center">
					<nobr>
						<img src="images/topall.gif" height="15" width="15" align="absmiddle" border="0">
						<a href="shiro-logout">退出系统</a>
					</nobr>
				</td>
			</tr>
		</tbody>
	</table>
</div>