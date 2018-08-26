package com.neuedu.dao.mabaits;

import com.neuedu.dao.LogDao;
import com.neuedu.entity.LogBean;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class LogMybatis implements LogDao {
    @Autowired
    SqlSession SqlSession;
    @Override
    public void add(LogBean LogBean) {
        LogDao  LogDao= SqlSession.getMapper(LogDao.class);
        LogDao.add(LogBean);
    }
}
