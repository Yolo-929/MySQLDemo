package dao;

import model.User;

public interface UserDao {
    //外界传递用户名和密码进来，我要在XML文档中查找是否有该条记录
    User find(String username, String password);

    //注册功能，外界传递一个User对象进来。我就在XML文档中添加一条信息
    void register(User user);
}
