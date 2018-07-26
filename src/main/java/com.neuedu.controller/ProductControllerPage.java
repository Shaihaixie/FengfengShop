package com.neuedu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.neuedu.entity.PageModel;
import com.neuedu.entity.Product;
import com.neuedu.service.ProductService;
import com.neuedu.service.impl.ProductServiceImpl;

@WebServlet("/view/productbypage")
public class ProductControllerPage extends HttpServlet {
	ProductService pService = new ProductServiceImpl();
 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		resp.setHeader("Access-Control-Allow-Origin", "*");
		String pageNo = req.getParameter("pageNo");
		String pageSize = req.getParameter("pageSize");
		int _pageNo = 1;
		int _pageSize = 6;
		try {
			if(pageNo!=null&&pageSize!=null) {
			_pageNo = Integer.parseInt(pageNo);
			_pageSize = Integer.parseInt(pageSize);}
		} catch (Exception e) {
			// TODO: handle exception
		}
		PageModel<Product> pageModel = pService.findProductByPage(_pageNo, _pageSize);
		req.setAttribute("pageModel", pageModel);
		 String info=JSONArray.toJSONString(pageModel);
	        resp.getWriter().print(info);
	req.getRequestDispatcher("ShopList.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	/**
	 * 
	 */

}
