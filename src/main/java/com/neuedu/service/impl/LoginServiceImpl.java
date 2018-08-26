package com.neuedu.service.impl;

import javax.servlet.http.HttpSession;

import com.neuedu.dao.ILoginDao;
import com.neuedu.dao.impl.jdbc.LoginDaoImpl;
import com.neuedu.dao.mabaits.LoginMybaits;
import com.neuedu.entity.Account;
import com.neuedu.service.ILoginService;
import com.neuedu.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service("loginService")
public class LoginServiceImpl implements ILoginService{
//	ILoginDao loginDao=new LoginDaoImpl();
@Autowired
     ILoginDao loginDao;
		//=new LoginMybaits();
	public Account  doLogin(String  username,String password) {
		//���е�¼��ҵ���߼�����
		 //LoginDao loginDao=new LoginDao(); 
		//LoginDaoMysql loginDao=new LoginDaoMysql();
		
		return loginDao.doLogin(username,password);
		
		
	}

	@Override
	public void addToken(String token,Account acc) {
		// TODO Auto-generated method stub
		loginDao.addToken(token, acc);
	}

	@Override
	public String findTokenByAccountid(int accountid) {
		// TODO Auto-generated method stub
		return loginDao.findTokenByAccountid(accountid);
	}
	
}
