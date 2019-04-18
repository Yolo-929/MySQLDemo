package dao;

import model.User;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserImplXML implements UserDao {
    //外界传递用户名和密码进来，我要在XML文档中查找是否有该条记录
    public User find(String username, String password) {
        //得到XML文档的流对象
        InputStream inputStream = UserImplXML.class.getClassLoader().getResourceAsStream("user.xml");
        //得到dom4j的解析器对象
        SAXReader saxReader = new SAXReader();
        try {
            //解析XML文档
            Document document = saxReader.read(inputStream);

            //使用XPATH技术，查找XML文档中是否有传递进来的username和password
            Element element = (Element) document.selectSingleNode("//user[@username='" + username + "' and@password='" + password + "']");

            if (element == null) {
                return null;
            }

            //如果有，就把XML查出来的节点信息封装到User对象，返回出去
            User user = new User();
            user.setId(Integer.parseInt(element.attributeValue("id")));
            user.setUsername(element.attributeValue("username"));
            user.setPassword(element.attributeValue("password"));
            user.setEmail(element.attributeValue("email"));

            //生日就需要转换一下了，XML文档保存的是字符串，User对象需要的是Date类型
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
            Date birthday = simpleDateFormat.parse(element.attributeValue("birthday"));
            user.setBirthday(birthday);

            //返回User对象出去
            return user;

        } catch (DocumentException e) {
            e.printStackTrace();
            throw new RuntimeException("初始化时候出错啦！");
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("查询的时候出错啦！");
        }
    }
    //注册功能，外界传递一个User对象进来。我就在XML文档中添加一条信息
    public void register(User user) {
        //获取XML文档路径！
        String path = UserImplXML.class.getClassLoader().getResource("user.xml").getPath();
        try {
            //获取dom4j的解析器，解析XML文档
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(path);

            //在XML文档中创建新的节点
            Element newElement = DocumentHelper.createElement("user");
            newElement.addAttribute("id", String.valueOf(user.getId()));
            newElement.addAttribute("username", user.getUsername());
            newElement.addAttribute("password", user.getPassword());

            if (user.getEmail() == null) {
                newElement.addAttribute("email", "");
            } else {
                newElement.addAttribute("email", user.getEmail());
            }

            //如果不是空才格式化信息
            if (user.getBirthday() == null) {
                newElement.addAttribute("birthday", "");
            } else {
                //日期返回的是指定格式的日期
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String date = simpleDateFormat.format(user.getBirthday());
                newElement.addAttribute("birthday", date);
            }

            //把新创建的节点增加到父节点上
            document.getRootElement().add(newElement);

            //把XML内容中文档的内容写到硬盘文件上
            OutputFormat outputFormat = OutputFormat.createPrettyPrint();
            outputFormat.setEncoding("UTF-8");
            XMLWriter xmlWriter = new XMLWriter(new FileWriter(path),outputFormat);
            xmlWriter.write(document);
            xmlWriter.close();

        } catch (DocumentException e) {
            e.printStackTrace();
            throw new RuntimeException("注册的时候出错了！！！");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("注册的时候出错了！！！");
        }
    }
}
