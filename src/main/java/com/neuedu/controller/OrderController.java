package com.neuedu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.neuedu.entity.Cart;
import com.neuedu.entity.PageModel;
import com.neuedu.entity.Product;
import com.neuedu.entity.UserOrder;
import com.neuedu.service.CartService;
import com.neuedu.service.OrderService;
import com.neuedu.service.ProductService;
import com.neuedu.service.impl.OrderServiceImpl;
import com.neuedu.service.impl.ProductServiceImpl;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@WebServlet("/view/Order")
public class OrderController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	OrderService orderService;
			//=new OrderServiceImpl();
			@Override
			public  void  init()  throws  ServletException{

//获取ioc容器
				WebApplicationContext mWebApplicationContext
						= WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
				orderService= (OrderService) mWebApplicationContext.getBean("orderService");

			}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		  String operation =req.getParameter("operation");
		  if(operation!=null&&!operation.equals("")) {
			  if(operation.equals("1")) {
				 createOrder(req, resp);
			}else if(operation.equals("2")) {
				 findAllOrder(req, resp);
			}
			else if(operation.equals("3")) {
				 findOrderByPage(req, resp);
			}
	}}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	//分页
	 public  void findOrderByPage(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
			 resp.setContentType("text/html;charset=utf-8");
				req.setCharacterEncoding("utf-8");
				String pageNo=  req.getParameter("pageNo");
				String pageSize=  req.getParameter("pageSize");
				int _pageNo=1;
				int _pageSize=5;
				try {
				 _pageNo=Integer.parseInt(pageNo);
				_pageSize=Integer.parseInt(pageSize);	
				} catch (Exception e) {
					// TODO: handle exception
				}
			PageModel<UserOrder> pageModel=orderService.findOrderByPage(_pageNo, _pageSize);
				req.setAttribute("pageModel", pageModel);
		 String  info= JSONArray.toJSONString(pageModel);
		 resp.getWriter().print(info);
		 req.setAttribute("pageModel", pageModel);
		// req.getRequestDispatcher("Order.jsp").forward(req, resp);
				
		    }



	public  void createOrder(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		UserOrder order=new UserOrder();
		  System.out.println(req.getParameter("id"));
		  boolean result=false;
		  int productid=0;
		  double total=0;
		  int productnum=0;
		  try {
			  total= Double.parseDouble(req.getParameter("total"));
			  productid=Integer.parseInt(req.getParameter("id"));
			  productnum= Integer.parseInt( req.getParameter("num"));
//			  ProductController  productController=new ProductController();
			  ProductService pService = new ProductServiceImpl();
			  Product  product= pService.findProductById(productid);
			  System.out.println(product.getDesc());
			  System.out.println(product.getName());
			     int fistnum=product.getStock();
			     System.out.println(fistnum);
			      System.out.println(productnum);
			     int lastnum=fistnum-productnum;
			     System.out.println(lastnum);
			      product.setStock(lastnum);
			  pService.updateProduct(product);
			     order.setOrder_no(System.currentTimeMillis());
			   order.setShipping_id(productid);;
			   order.setProduct(product);
			   order.setPayment(total);
			  result=  orderService.createOrder();
		} catch (Exception e) {
			// TODO: handle exception
		}
		  if(result) {
	        	 System.out.println("创建成功");
	        	 findOrderByPage(req, resp);
	        	
	         }else {
	        	 System.out.println("创建失败");
			}
	    
	    }
	public  void findAllOrder(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		List<UserOrder> userOrders= orderService.findAllorder();
		 req.setAttribute("userOrders", userOrders);
		req.getRequestDispatcher("Order.jsp").forward(req, resp);
	}
	
	public  boolean  createOrder(UserOrder userOrder) {
		return orderService.createOrder(userOrder);
		
	}
	
	
}
