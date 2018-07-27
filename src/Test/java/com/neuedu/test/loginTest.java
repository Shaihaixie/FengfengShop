package com.neuedu.test;

import com.neuedu.dao.ILoginDao;
import com.neuedu.dao.mabaits.LoginMybaits;
import com.neuedu.entity.Account;
import org.junit.Test;

public class loginTest {
    @Test
    public  void  login(){
        ILoginDao ILoginDao=new LoginMybaits();
       Account account= ILoginDao.doLogin("admin","21232f297a57a5a743894a0e4a801fc3");
        System.out.println(account);
    }

}
