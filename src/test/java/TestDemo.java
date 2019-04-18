import dao.UserDao;
import dao.UserImplXML;
import model.User;
import org.junit.Test;

import java.util.Date;

public class TestDemo {
    private String username = "yolo";
    private String password = "123";

    @Test
    public void testLogin() {

        UserDao userImplXML = new UserImplXML();
        User user = userImplXML.find(username, password);

        System.out.println(user.getBirthday());
        System.out.println(user.getEmail());
        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
    }
    @Test
    public void testRegister() {

        UserDao userImplXML = new UserImplXML();

        //这里我为了测试的方便，就添加一个带5个参数的构造函数了！
        User user = new User(10, "yoyoyo", "123", "sina@qq.com", new Date());

        userImplXML.register(user);
    }
}
