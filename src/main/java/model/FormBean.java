package model;

import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import java.util.HashMap;

public class FormBean {

    //表单提交过来的数据全都是String类型的，birthday也不例外！
    private String username;
    private String password;
    private String password2;
    private String email;
    private String birthday;

    //记录错误的信息
    private HashMap<String, String> errors = new HashMap<String, String>();

    /*用于判断表单提交过来的数据是否合法*/
    public boolean validate() {
        boolean flag=true;
        //用户名不能为空，并且要是3-8的字符 abcdABcd
        if (this.username == null || this.username.trim().equals("")) {
            errors.put("username", "用户名不能为空，并且要是3-8的字符");
            flag = false;
        } else {
            if (!this.username.matches("[a-zA-Z]{3,8}")) {
                errors.put("username", "用户名不能为空，并且要是3-8的字符");
                flag = false;
            }
        }

        //密码不能为空，并且要是3-8的数字
        if (this.password == null || this.password.trim().equals("")) {
            errors.put("password", "密码不能为空，并且要是3-8的数字");
            flag = false;
        } else {
            if (!this.password.matches("\\d{3,8}")) {
                errors.put("password", "密码不能为空，并且要是3-8的数字");
                flag = false;
            }
        }

        //两次密码要一致
        if (this.password2 != null && !this.password2.trim().equals("")) {
            if (!this.password2.equals(this.password)) {
                errors.put("password2", "两次密码要一致");
                flag = false;
            }
        }

        //邮箱可以为空，如果为空就必须合法
        if (this.email != null && !this.email.trim().equals("")) {
            if (!this.email.matches("\\w+@\\w+(\\.\\w+)+")) {

                errors.put("email", "邮箱不合法！");
                flag = false;
            }
        }

        //日期可以为空，如果为空就必须合法
        if (this.birthday != null && !this.birthday.trim().equals("")) {
            try {
                DateLocaleConverter dateLocaleConverter = new DateLocaleConverter();
                dateLocaleConverter.convert(this.birthday);
            } catch (Exception e) {
                errors.put("birthday", "日期不合法！");
               flag= false;
            }
        }

        //如果上面都没有执行，那么就是合法的了，返回true
        return flag;
    }

    public HashMap<String, String> getErrors() {
        return errors;
    }

    public void setErrors(HashMap<String, String> errors) {
        this.errors = errors;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
