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
  @Test
     public  void  addToken(){
      ILoginDao ILoginDao=new LoginMybaits();
      Account acc= ILoginDao.doLogin("admin","123456");
      System.out.println(acc);
         ILoginDao.addToken("5555555",acc);
  }
     @Test
    public   void  findTokenByAccountid(){
         ILoginDao ILoginDao=new LoginMybaits();
         String A=  ILoginDao.findTokenByAccountid(1);
         System.out.println(A);
     }
}
