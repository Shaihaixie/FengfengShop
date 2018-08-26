package com.neuedu.test;

import com.neuedu.service.AccountService;


import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class accountTest {
    ApplicationContext ApplicationContext=null;
    @Before
    public  void before(){
        ApplicationContext= new ClassPathXmlApplicationContext("Spring-config.xml");
    }
    @Test
    public  void test(){
        AccountService accountService=ApplicationContext.getBean(AccountService.class);
        accountService.transfer("admin","aaaa",100);
    }
}
