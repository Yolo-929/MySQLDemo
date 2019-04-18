<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/18 0018
  Time: 上午 8:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>这是登陆界面</h1>

<form action="${pageContext.request.contextPath}/LoginServlet" method="post">
    <table>
        <tr>
            <td>用户名</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td><input type="submit" value="提交"></td>
            <td><input type="reset" name="重置"></td>
        </tr>
    </table>
</form>
</body>
</html>
