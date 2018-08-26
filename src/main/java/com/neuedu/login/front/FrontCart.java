package com.neuedu.login.front;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import com.neuedu.entity.Cart;
import com.neuedu.entity.PageModel;
import com.neuedu.service.CartService;
import com.neuedu.service.impl.CartServiceImpl;

/**
 * Servlet implementation class FrontCart
 */
@WebServlet("/front/cart.do")
public class FrontCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		CartService cartService=new CartServiceImpl();
		String pageNo=  request.getParameter("pageNo");
		String pageSize=  request.getParameter("pageSize");
		int _pageNo=1;
		int _pageSize=5;
		try {
		 _pageNo=Integer.parseInt(pageNo);
		_pageSize=Integer.parseInt(pageSize);	
		} catch (Exception e) {
			// TODO: handle exception
		}
		PageModel<Cart> pageModel=cartService.findCartByPage(_pageNo, _pageSize);
		//´«Ò³Ãæ
		PrintWriter pw=response.getWriter();
		String info=JSONArray.toJSONString(pageModel);
        response.getWriter().print(info);
		System.out.println(info);
		pw.write("data("+info+")");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
