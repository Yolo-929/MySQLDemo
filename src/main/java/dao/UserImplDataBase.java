package dao;

import model.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import util.Utils2DB;

import java.sql.SQLException;
import java.util.Date;

public class UserImplDataBase implements  UserDao {
    public User find(String username, String password) {

        QueryRunner queryRunner = new QueryRunner(Utils2DB.getDataSource());

        String sql = "SELECT * FROM user WHERE username=? AND password=?";

        try {
            User user = (User) queryRunner.query(sql, new BeanHandler(User.class), new Object[]{username, password});

            return user == null ? null : user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("登陆失败了！");
        }
    }

    public void register(User user) {

        QueryRunner queryRunner = new QueryRunner(Utils2DB.getDataSource());

        String sql = "INSERT INTO user (id, username, password, email,birthday) VALUES (?,?,?,?,?);";

        int id = user.getId();
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();

        Date date = user.getBirthday();


        try {
            queryRunner.update(sql, new Object[]{id, username, password, email,date});

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("注册失败了");
        }
    }
}
