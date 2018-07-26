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
@WebServlet("/view/Cart")
public class CartController extends HttpServlet{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
CartService cartService=new CartServiceImpl();
ProductService  pService=new ProductServiceImpl();
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
	}
}
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	doGet(req, resp);
}
//��ҳ
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
			req.getRequestDispatcher("cart.jsp").forward(req, resp);
			//��ҳ��
			 PrintWriter pw=resp.getWriter();
		     String info=JSONArray.toJSONString(pageModel);
		     resp.getWriter().print(info);
     	     System.out.println(info);
//     		  pw.write("data("+info+")");
//			PrintWriter pw=resp.getWriter();
//			Gson  gson=new Gson();
//			String result=gson.toJson(pageModel);
//			System.out.println(result);
//			pw.write("data("+result+")");
			
	    }





/**
 * �޸Ĺ��ﳵ
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
		 System.out.println("���ﳵ�޸ĳɹ�");
			findCartByPage(req, resp);
		}
     	else {
     	 System.out.println("���ﳵ�޸�ʧ��");
		}
		         
		} catch (Exception e) {
			// TODO: handle exception
		}
			
	}
else {
    	System.out.println("�Ҳ���");
    }
}


/**
 * ɾ�����ﳵ
 * 
 **/
public void deleteCart(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	
	boolean flag=cartService.deleteCart(Integer.parseInt(request.getParameter("id")));
	
	if(flag) {
		System.out.println("ɾ���ɹ�");
		findCartByPage(request, response);
		
	}else {
		System.out.println("ɾ��ʧ��");
	}
}
/**
 * �������ﳵ
 * 
 **/
public  void findAll(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
	List<Cart> carts= cartService.findAllCart();
//	String info=JSONArray.toJSONString(carts);
//	resp.getWriter().print(info);
	 req.setAttribute("carts", carts);
	req.getRequestDispatcher("cart.jsp").forward(req, resp);
}

	/**
	 * ��ӹ��ﳵ
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
		  ProductController  productController=new ProductController();
		   Product  product= productController.findProductById(productid);
		    cart.setProductid(productid);
		  cart.setProduct(product);
		  cart.setProductNum(productnum);
		  result= addCart(cart);
	} catch (Exception e) {
		// TODO: handle exception
	}
	  if(result) {
        	 System.out.println("���ﳵ��ӳɹ�");
        	findCartByPage(req, resp);
        	
         }else {
        	 System.out.println("���ﳵ���ʧ��");
		}
    
    }

	public boolean  addCart(Cart cart) {
		
		return cartService.addCart(cart);
	}
	/**
	 * ɾ��
	 * */
	public boolean  deleteCart(int id) {
		return cartService.deleteCart(id);
	}
	/**
	 * �޸Ĺ��ﳵ
	 * */
	public boolean  updataeCart(Cart cart) {
		return false;
	}
	/**
	 * ��ѯ���ﳵ
	 * */
	public List<Cart> findAllCart(){
		
		return cartService.findAllCart();
	}
	/**
	 * ��ȡ���ﳵ����Ʒ����
	 * */
	public int  getCartNum() {
		return cartService.getCartNum();
	};
	
	/**�޸Ĺ��ﳵ��Ʒ����
	 * @param  id  Ҫ�޸ĵĹ��ﳵ��Id
	 * @param  num �޸ĺ������
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
