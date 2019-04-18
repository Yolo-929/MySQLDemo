package util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

public class WebUtils {
    public static int makeId() {
        BigInteger bigInteger=new BigInteger(UUID.randomUUID().toString().replace("-",""),16);
        return bigInteger.intValue();
    }
    public static <T> T request2Bean(HttpServletRequest httpServletRequest, Class<T> tClass) {

        try {
            //创建tClass的对象
            T bean = tClass.newInstance();

            //获取得到Parameter中全部的参数的名字
            Enumeration enumeration = httpServletRequest.getParameterNames();

           ConvertUtils.register(new DateLocaleConverter(), Date.class);
            //遍历上边获取得到的集合
            while (enumeration.hasMoreElements()) {

                //获取得到每一个带过来参数的名字
                String name = (String) enumeration.nextElement();

                //获取得到值
                String value = httpServletRequest.getParameter(name);
                //如果Parameter中的数据为""，那么我就不封装到User对象里边去！执行下一次循环
                if (value .equals("") ){
                    continue;
                } else {
                    //把数据封装到Bean对象中
                    BeanUtils.setProperty(bean, name, value);
                }


            }
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("封装数据到Bean对象中出错了！");
        }
    }
}
