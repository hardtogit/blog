<#include "header.ftl">
<table border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td style="padding-left: 20px; color: #808080; font-size: 14px;">
			<p style="margin-bottom: 5px; margin-top: 20px;">欢迎您申请加入道简科技有限公司网站会员。</p>
		    <p style="margin-bottom: 5px; margin-top: 20px;">您的会员账号已经成功创建，请妥善保管这封电子邮件，账号资料如下：</p>
		    <p style="margin-bottom: 5px; margin-top: 20px;">账号 / 密码 : ${user.loginName} / ${user.plainPassword}</p>
		    <p style="margin-bottom: 5px; margin-top: 20px;">请点击Active Click激活账号。</p>
		    <p><a href="${activeUrl}">Active Click</a></p>
		    <p style="margin-bottom: 5px; margin-top: 20px;">如果无法点击Click，请将如下URL复制并粘帖到浏览器的地址输入框，然后单击回车即可。该链接使用后将立即失效。</p>
		    <p>${activeUrl}</p>
		</td>
	</tr>
</table>
<#include "footer.ftl">