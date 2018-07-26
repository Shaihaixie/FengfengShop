package com.neuedu.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.neuedu.entity.Account;
import com.neuedu.service.ILoginService;
import com.neuedu.service.impl.LoginServiceImpl;
import com.neuedu.utils.MD5Utils;


/**
 * 负责接收用户用户名、密码
 * */
@WebServlet("/login.do")
public class LoginController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ILoginService  loginService=new LoginServiceImpl();
		
		 String  name=req.getParameter("name");
		 String   pas=req.getParameter("pas");   
		Account account=loginService.doLogin(name, MD5Utils.GetMD5Code(pas));
		if(account!=null) {
			//登录成功
			Cookie cookie= new Cookie("name",name);
			cookie.setMaxAge(7*24*3600);
			resp.addCookie(cookie);
			Cookie pwd_cookie= new Cookie("pas", MD5Utils.GetMD5Code(pas));
			pwd_cookie.setMaxAge(7*24*3600);
			resp.addCookie(pwd_cookie);
			//生产token，放在数据库中	
			long  time=System.currentTimeMillis();
			String token= MD5Utils.GetMD5Code(name+pas+time);
			loginService.addToken(token,account);
	          //放到会话域
		HttpSession	session=req.getSession();
			session.setAttribute("token", token);
			session.setAttribute("acc", account);
			req.getRequestDispatcher("home.jsp").forward(req, resp);
			
		}else {
			req.getRequestDispatcher("fail.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

	/**
	 * @return boolean true:登录成功 false：登录失败
	 * */
	public  Account login(String username,String password) {
		
		ILoginService  loginService=new LoginServiceImpl();
		return loginService.doLogin(username, password);
		
		
	}
	
}
