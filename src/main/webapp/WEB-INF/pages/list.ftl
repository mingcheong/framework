<html>
  <body>
    <table border="1">
		<#list userList as user>
    	<tr>
    		<td>${user.id}</td>
    		<td>${user.name}</td>
    		<td>${user.age}</td>
    	</tr>
    	</#list>
    </table>
  </body>  
</html>