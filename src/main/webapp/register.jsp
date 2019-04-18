<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/16 0016
  Time: 下午 4:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>欢迎来到注册界面！</h1>

<%--提交给处理注册的处理Servlet--%>
<form method="post" action="${pageContext.request.contextPath}/RegisterServlet">

    <table>
        <%--对于id来讲，是服务器分配的！不需要用户自己输入--%>
        <tr>
            <td>用户名</td>
            <td>
                <input type="text" name="username" value="${formbean.username}">${formbean.errors.username}
            </td>
        </tr>
        <tr>
            <td>密码</td>
            <td>
                <input type="text" name="password" value="${formbean.password}">${formbean.errors.password}
            </td>
        </tr>
        <tr>
            <td>确认密码</td>
            <td>
                <input type="text" name="password2" value="${formbean.password2}">${formbean.errors.password2}
            </td>
        </tr>
        <tr>
            <td>邮箱</td>
            <td>
                <input type="text" name="email" value="${formbean.email}">${formbean.errors.email}
            </td>
        </tr>
        <tr>
            <td>生日</td>
            <td>
                <input type="text " name="birthday" value="${formbean.birthday}">${formbean.errors.birthday}
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="提交">
            </td>
            <td>
                <input type="reset" value="重置！">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
