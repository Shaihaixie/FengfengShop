package com.neuedu.login.front;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.entity.Account;
import com.neuedu.service.ILoginService;
import com.neuedu.service.impl.LoginServiceImpl;
import com.neuedu.utils.MD5Utils;

/**
 * Servlet implementation class FrontLogin
 */
@WebServlet("/front/login.do")
public class FrontLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ILoginService  loginService=new LoginServiceImpl();
		
		 String  name=request.getParameter("name");
		 String   pas=request.getParameter("pas");   
		Account account=loginService.doLogin(name, MD5Utils.GetMD5Code(pas));
		if(account!=null) {
//			accountId;
//			public  String  username;
//			public  String  password;
//			public  String  ip;
//			public  String  sex
			StringBuilder sbuffer=new StringBuilder("{");
			
			sbuffer.append("\"");
			sbuffer.append("username");
			sbuffer.append("\"");
			sbuffer.append(":");
			sbuffer.append("\"");
			sbuffer.append(account.getUsername());
			sbuffer.append("\"");
			sbuffer.append(",");
			
			sbuffer.append("\"");
			sbuffer.append("password");
			sbuffer.append("\"");
			sbuffer.append(":");
			sbuffer.append("\"");
			sbuffer.append(account.getPassword());
			sbuffer.append("\"");
			
			sbuffer.append("}");	
			System.out.println(sbuffer.toString());
			//获取输出流
			PrintWriter writer=response.getWriter();
			writer.println("success("+sbuffer.toString()+")");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
