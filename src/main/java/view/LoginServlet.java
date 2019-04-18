package view;

import model.User;
import service.ServiceBussiness;
import service.UserServiceXML;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取提交过来的数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //调用service层的方法，去查询数据库（XML）是否有该条记录
        try {
            ServiceBussiness serviceBussiness = new UserServiceXML();
            User user = serviceBussiness.login(username, password);

            if (user == null) {
                request.setAttribute("message", "用户名或密码是错的");
            } else {
                request.setAttribute("message","登陆成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message","登陆失败咯");
        }
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
