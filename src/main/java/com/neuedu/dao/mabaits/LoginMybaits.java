package com.neuedu.dao.mabaits;

import com.neuedu.dao.ILoginDao;
import com.neuedu.entity.Account;
import com.neuedu.entity.LogBean;
import com.neuedu.utils.MyBatisFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
@Repository
public class LoginMybaits implements ILoginDao {
    @Autowired
    private      SqlSession sqlSession;
    @Override
    public Account doLogin(String _username, String _password) {
//        1，读取配置文件 ，读取配置文件
//        2，生成 ，生成SqlSessionFactory  为SqlSession的工厂，用于建立与数据库的会话。
//        3，建立 ，建立SqlSession
//        用于执行sql语句
//        4，调用 ，调用MyBatis提供的 提供的api
//        5，查询 ，查询MAP配置 配置
//        6，返回结果 ，返回结果
//        7，关闭 ，关闭SqlSession
//        String resource = "mybatis-config.xml";
//        Reader reader = null;
//        SqlSession session;
//        try {
//            reader = Resources.getResourceAsReader(resource);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//             e.printStackTrace();  }
//            SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
//            session = sqlMapper.openSession();
        /**
         * namespace+id
         */
      //  SqlSession session = MyBatisFactory.getSqlSession();
        Map<String, String> map = new HashMap<String, String>();
        map.put("username", _username);
        map.put("password", _password);
        Account account = sqlSession.selectOne("com.neuedu.entity.Account.findByUsernameAndPassword", map);
        System.out.println(account);
        //sqlSession.close();
        return account;
    }
    @Override
    public void addToken(String token, Account acc) {
//        String resource = "mybatis-config.xml";
//        Reader reader = null;
//        SqlSession session;
//        try {
//            reader = Resources.getResourceAsReader(resource);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//             e.printStackTrace();  }
//            SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
//            session = sqlMapper.openSession(true);
        //SqlSession session = MyBatisFactory.getSqlSession();
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", token);
        map.put("accountid", acc.getAccountId() + "");
        sqlSession.update("com.neuedu.entity.Account.addToken", map);
    }

    @Override
    public String findTokenByAccountid(int accountid) {
        //SqlSession session = MyBatisFactory.getSqlSession();
        return sqlSession.selectOne("com.neuedu.entity.Account.findTokenByAccountid", accountid);
    }

    @Override
    public void updateAccount(String username, int money) {
        ILoginDao ILoginDao=sqlSession.getMapper(com.neuedu.dao.ILoginDao.class);
        ILoginDao.updateAccount(username,money);
    }


}
