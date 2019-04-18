package view;

import model.FormBean;
import model.User;
import org.apache.commons.beanutils.BeanUtils;
import service.ServiceBussiness;
import service.UserServiceXML;
import util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //将表单的数据封装到formBean中
        FormBean formBean = WebUtils.request2Bean(request, FormBean.class);

        //验证表单的数据是否合法，如果不合法就跳转回去注册的页面
        if(formBean.validate()==false){
            //在跳转之前，把formbean对象传递给注册页面
            request.setAttribute("formbean", formBean);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        try {

            //这是第一种
           /*User user = WebUtils.request2Bean(request,User.class);
            user.setId(WebUtils.makeId());*/
            //-----------------------------------

            //这是第二种--------------------------
            User user = new User();
            user.setId(WebUtils.makeId());
            BeanUtils.copyProperties(user,formBean);
            //------------------------------------------

            //调用service层的注册方法，实现注册
            ServiceBussiness serviceBussiness = new UserServiceXML();
            serviceBussiness.register(user);
            request.setAttribute("message","注册成功！");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message","注册失败！");
        }
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
