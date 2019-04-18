package service;

import dao.UserDao;
import dao.UserImplXML;
import model.User;

public class UserServiceXML implements ServiceBussiness {

    //Service层就是调用Dao层的方法，我们就直接在类中创建Dao层的对象了
    private UserDao userImplXML = new UserImplXML();

    public void register(User user) {
        userImplXML.register(user);
    }

    public User login(String username, String password) {
        return userImplXML.find(username, password);
    }
}
