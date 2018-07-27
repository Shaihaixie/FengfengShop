package com.neuedu.utils;
import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
public class MyBatisFactory {

    public  static SqlSessionFactory  getSqlSessionFactory() {
        String resouses="mybatis-config.xml";
        InputStream input;

        try {
            input=Resources.getResourceAsStream(resouses);
            SqlSessionFactory sf=new SqlSessionFactoryBuilder().build(input);
            return sf;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;


    }
    public static SqlSession getSqlSession() {
        return getSqlSessionFactory().openSession();

    }

}
