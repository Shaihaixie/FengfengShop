package com.neuedu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import com.neuedu.entity.Cart;
import com.neuedu.entity.Category;
import com.neuedu.entity.PageModel;
import com.neuedu.entity.Product;
import com.neuedu.service.CartService;
import com.neuedu.service.ProductService;
import com.neuedu.service.impl.CartServiceImpl;
import com.neuedu.service.impl.ProductServiceImpl;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@WebServlet("/view/Cart")
public class CartController extends HttpServlet{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
CartService cartService;
	ProductService pService ;
 @Override
		public  void  init()  throws  ServletException{

//获取ioc容器
	 WebApplicationContext mWebApplicationContext
			 = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
	 cartService= (CartService) mWebApplicationContext.getBean("cartService");
	 pService= (ProductService) mWebApplicationContext.getBean("pService");
 }
	//ProductService  pService=new ProductServiceImpl();
	@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub

		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		resp.setHeader("Access-Control-Allow-Origin", "*");
		  String operation =req.getParameter("operation");
		  if(operation!=null&&!operation.equals("")) {
			  if(operation.equals("1")) {
   			  addCart(req,resp);
			}else if (operation.equals("2")) {
				 findAll(req,resp);
			}else if (operation.equals("3")) {
				deleteCart(req,resp);
			}
			else if (operation.equals("4")) {
				findCartById(req, resp);
			}
			else if (operation.equals("5")) {
				updateCart(req, resp);
			}
			else if (operation.equals("6")) {
				findCartByPage(req, resp);
			}else if (operation.equals("7")) {
				findCartById2(req, resp);
			}
			  else if (operation.equals("8")) {
				  findCartByPage2(req, resp);
			  }

	}
}
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	doGet(req, resp);
}
//分页
 public  void findCartByPage(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
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
			PageModel<Cart> pageModel=cartService.findCartByPage(_pageNo, _pageSize);
			req.setAttribute("pageModel", pageModel);
//		req.getRequestDispatcher("cart.jsp").forward(req, resp);
			//传页面
  		      PrintWriter pw=resp.getWriter();
     String     callback=     req.getParameter("callback");
  		       Gson gson=new Gson();
  		        String json=gson.toJson(pageModel);
            pw.write(callback+"("+json+")");
	        System.out.println(json);
//	            String     callback=     req.getParameter("callback");
//		     String info=JSONArray.toJSONString(pageModel);
		    //   resp.getWriter().print(info);
     	    // System.out.println(info);
//     		  pw.write("data("+info+")");
//			PrintWriter pw=resp.getWriter();
//			Gson  gson=new Gson();
//			String result=gson.toJson(pageModel);
//			System.out.println(result);
//			pw.write("data("+result+")");
			
	    }
//后台分页
	public  void findCartByPage2(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
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
		PageModel<Cart> pageModel=cartService.findCartByPage(_pageNo, _pageSize);
		req.setAttribute("pageModel", pageModel);
        req.getRequestDispatcher("cart.jsp").forward(req, resp);
	}



/**
 * 修改购物车
 * 
 **/
public  void  updateCart(HttpServletRequest req,HttpServletResponse resp) {
	Cart cart= cartService.findCartById(Integer.parseInt(req.getParameter("id")));
	if(cart.getId()!=0) {
		  int id=0;
		  int productid=0;
		  int productnum=0;
		  try {
			  id=Integer.parseInt(req.getParameter("id"));
			  productid=Integer.parseInt(req.getParameter("productid"));
			  productnum=Integer.parseInt(req.getParameter("productnum"));
			  cart.setProductNum(productnum);	
		      boolean flag= cartService.updataeCart(cart);
			if(flag) {
		 System.out.println("购物车修改成功");
			findCartByPage(req, resp);
		}
     	else {
     	 System.out.println("购物车修改失败");
		}
		         
		} catch (Exception e) {
			// TODO: handle exception
		}
			
	}
else {
    	System.out.println("找不到");
    }
}


/**
 * 删除购物车
 * 
 **/
public void deleteCart(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	boolean flag=cartService.deleteCart(Integer.parseInt(request.getParameter("id")));
	if(flag) {
		System.out.println("删除成功");
		findCartByPage(request,response);
	}else {
		System.out.println("删除失败");
	}
}
/**
 * 遍历购物车
 * 
 **/
public  void findAll(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
	List<Cart> carts= cartService.findAllCart();
String info=JSONArray.toJSONString(carts);
	resp.getWriter().print(info);
	 req.setAttribute("carts", carts);
	//req.getRequestDispatcher("cart.jsp").forward(req, resp);
}

	/**
	 * 添加购物车
	 * 
	 **/
public  void addCart(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
	Cart cart=new Cart();
	  int productid=0;
	  int productnum=0;
	  System.out.println(req.getParameter("pid"));
	  boolean result=false;
	  try {
		  productid=Integer.parseInt(req.getParameter("pid"));
		   productnum=Integer.parseInt(req.getParameter("productnum"));
	    //	ProductController  productController=new ProductController();
		   Product  product= pService.findProductById(productid);
		   cart.setProductid(productid);
		  cart.setProduct(product);
		  cart.setProductNum(productnum);
		  result= addCart(cart);
	} catch (Exception e) {
		// TODO: handle exception
	}
	  if(result) {
        	 System.out.println("购物车添加成功");
        	findCartByPage(req, resp);
        	
         }else {
        	 System.out.println("购物车添加失败");
		}
    
    }

	public boolean  addCart(Cart cart) {
		
		return cartService.addCart(cart);
	}
	/**
	 * 删除
	 * */
	public boolean  deleteCart(int id) {
		return cartService.deleteCart(id);
	}
	/**
	 * 修改购物车
	 * */
	public boolean  updataeCart(Cart cart) {
		return false;
	}
	/**
	 * 查询购物车
	 * */
	public List<Cart> findAllCart(){
		
		return cartService.findAllCart();
	}
	/**
	 * 获取购物车中商品数量
	 * */
	public int  getCartNum() {
		return cartService.getCartNum();
	};
	
	/**修改购物车商品数量
	 * @param  id  要修改的购物车的Id
	 * @param  num 修改后的数量
	 * */
	public boolean  updateCart(int id,int num) {
		return  cartService.updateCart(id, num);
	};
	 public void findCartById(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
	    	String sid=req.getParameter("id");
	    	int id=0;
	    	try {
				id=Integer.parseInt(sid);
				Cart cart= cartService.findCartById(id);
				if(cart!=null) {
					req.setAttribute("cart", cart);
					req.getRequestDispatcher("UpCart.jsp").forward(req, resp);
				}				
			} catch (Exception e) {
				// TODO: handle exception
			}
	    }
		public void  findCartById2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String sid=req.getParameter("id");
	    	int id=0;
	    	try {
				id=Integer.parseInt(sid);
				Product product= pService.findProductById(id);
				if(product!=null) {
					req.setAttribute("product", product);
					req.getRequestDispatcher("BuyShop.jsp").forward(req, resp);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		} 
	 
	 
}
