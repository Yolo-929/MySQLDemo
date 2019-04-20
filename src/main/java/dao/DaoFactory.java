package dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DaoFactory {

    private static UserDao userDao = null;
    private static final DaoFactory DAO_FACTORY = new DaoFactory();
    private DaoFactory() {
        try {
            //读取配置文件的信息
            InputStream inputStream = DaoFactory.class.getClassLoader().getResourceAsStream("UserDao.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            String userClass = properties.getProperty("userClass");

            //利用反射机制创建相对应的对象
            userDao = (UserDao) Class.forName(userClass).newInstance();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("读取文件失败了！");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("反射失败了！");
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException("反射失败了！");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("反射失败了！");
        }
    }

    //暴露公开方法获取工厂对象
    public static DaoFactory newInstance() {
        return DAO_FACTORY;
    }

    public static UserDao createUserDao() {
        return userDao;
    }
}
