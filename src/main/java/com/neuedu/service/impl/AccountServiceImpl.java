package com.neuedu.service.impl;

import com.neuedu.dao.ILoginDao;
import com.neuedu.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("AccountServiceImpl")
public class AccountServiceImpl implements AccountService {
   @Autowired
    ILoginDao loginDao;
    @Override
//    @Transactional
    public void transfer(String fromuser, String touser, int money) {
        loginDao.updateAccount(fromuser,1000-money);
        loginDao.updateAccount(touser,1000+money);
    }
}
