<form action="<%=request.getContextPath()%>/j_spring_security_check"
	method="post">
	用户名：<input type="text" name="j_username"
		value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}" /></br> 密 码：<input
		type="password" name="j_password" value="" /></br> <input type="checkbox"
		name="_spring_security_remember_me" />两周之内不必登陆 <input type="submit"
		value="登陆">
</form>
