package com.neuedu.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.neuedu.entity.Category;
import com.neuedu.entity.PageModel;
import com.neuedu.entity.Product;
import com.neuedu.service.CategoryService;
import com.neuedu.service.ProductService;
import com.neuedu.service.impl.CategoryServiceImpl;
import com.neuedu.service.impl.ProductServiceImpl;

/**
 * Servlet implementation class CategoryController
 */
@WebServlet("/view/Category")
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CategoryService cService=new CategoryServiceImpl();
	ProductService pService=new ProductServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		  String operation =req.getParameter("operation");
		  if(operation!=null&&!operation.equals("")) {
			  if(operation.equals("1")) {
				  findCategoryById(req,resp);
			  }else  if(operation.equals("2")){
				findAll(req, resp);
			}else if (operation.equals("3")) {
				deleteCategory(req,resp);	
			}
			else if (operation.equals("4")) {
				addCategory(req,resp);
			}
			else if (operation.equals("5")) {
			updateCategory(req, resp);
			}else if (operation.equals("6")) {
				findAllshop(req, resp);
				}
			else if(operation.equals("7")) {
				findCategoryByPage(req, resp);
			}
			else if(operation.equals("8")) {
				findparentName(req, resp);
			}if(operation.equals("9")) {
				findSontName(req, resp);
			}
	}
		  }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	  public  void findparentName(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
	    	List<Category> Categorys= cService.findAllparentname();
	    	req.setAttribute("Categorys", Categorys);
	    	req.getRequestDispatcher("showNewcate.jsp").forward(req, resp);
	    }
	
	  public  void findSontName(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
	    	    String   sonname=req.getParameter("name");
		  List<Category> Categoryson= cService.findAllsonname(sonname);
	    	  req.setAttribute("Categoryson", Categoryson);
	    	req.getRequestDispatcher("showNewcate.jsp").forward(req, resp);
	    }
	
	
	
	 public  void findCategoryByPage(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
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
			PageModel<Category> pageModel=cService.findCategoryByPage(_pageNo, _pageSize);
			req.setAttribute("pageModel", pageModel);
			req.getRequestDispatcher("ShopType.jsp").forward(req, resp);
	    }
	 public void findCategoryById(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
	    	String sid=req.getParameter("id");
	    	int id=0;
	    	try {
				id=Integer.parseInt(sid);
				Category categorys= cService.findById(id);
				if(categorys!=null) {
					req.setAttribute("categorys", categorys);
					req.getRequestDispatcher("UpShopType.jsp").forward(req, resp); 	
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
	    }
	    public Category findById(int id) {
	    	return cService.findById(id);
	    }
	    public  void findAllshop(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
	    	List<Product> products= pService.findBycategory_id(Integer.parseInt(req.getParameter("id")));
	    	req.setAttribute("products", products);
	    	req.getRequestDispatcher("SonShop.jsp").forward(req, resp);
	    }
	    public  void findAll(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
	    	List<Category> Categorys= cService.findAll();
	    	req.setAttribute("Categorys", Categorys);
	    	req.getRequestDispatcher("shoptype.jsp").forward(req, resp);
	    }
	    public void deleteCategory(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	    	boolean flag=cService.deleteCategory(Integer.parseInt(request.getParameter("id")));
	    	if(flag) {
	    		System.out.println("删除成功");
	    		findCategoryByPage(request, response);
	    		
	    	}else {
	    		System.out.println("删除失败");
	    	}
	    }
	    public  void addCategory(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
	    	Category category=new Category();
	    	  int id=0;
	    	  int parent_id=0;
	    	  int status=0;
	    	  int sort_order=0;
			  String cname=req.getParameter("cname");
			  boolean result=false;
			  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			  try {
				  id=Integer.parseInt(req.getParameter("id"));
//				  parent_id=Integer.parseInt(req.getParameter("parent_id"));
				  status=Integer.parseInt(req.getParameter("status"));
				  sort_order=Integer.parseInt(req.getParameter("sort_order"));
				  String create_time=req.getParameter("create_time");
				  String update_time=req.getParameter("update_time");
				  category.setId(id);
//				  category.setParent_id(parent_id);
				  category.setCname(cname);
				  category.setStatus(status);
				  category.setSort_order(sort_order);
				  category.setCreate_time(create_time);
				  category.setUpdate_time(update_time);
				  result= addCategory(category);
			        
			} catch (Exception e) {
				// TODO: handle exception
			}
			  if(result) {
		        	 System.out.println("修改類別成功");
		        	 findCategoryByPage(req, resp);
		         }else {
		        	 System.out.println("修改類別失敗");
				}
		    
		    }
	    public  boolean addCategory(Category category) {
	    	return cService.addCategory(category);
	    }
	    /**修改类别*/
	    public  void  updateCategory(HttpServletRequest req,HttpServletResponse resp) {	
	    	Category category= cService.findById(Integer.parseInt(req.getParameter("id")));
	    	if(category.getCname()!=null) {
	    		String id=req.getParameter("id");
//	   		  String Parent_id=req.getParameter("parent_id");
	   		  String cname=req.getParameter("cname");
	   		  String status=req.getParameter("status");
	   		 String sort_order=req.getParameter("sort_order");
	   		 String create_time=req.getParameter("create_time");
	   		 String update_time=req.getParameter("update_time");
	   		  try {
	   			 category.setId(Integer.parseInt(id));
//				  category.setParent_id(Integer.parseInt(Parent_id));
				  category.setCname(cname);
				  category.setStatus(Integer.parseInt(status));
				  category.setSort_order(Integer.parseInt(sort_order));
				  category.setCreate_time(create_time);
				  category.setUpdate_time(update_time);
	   		      boolean flag= cService.updateCategory(category);
	    			if(flag) {
	    		 System.out.println("修改类别成功");
	    		   findCategoryByPage(req, resp);
	    		}
	         	else {
	         	 System.out.println("修改类别失败");
	  		}
	   		         
	   		} catch (Exception e) {
	   			// TODO: handle exception
	   		}}else {
			System.out.println("閿熸彮璇ф嫹閿熸枻鎷�");
		}
	    }
	 
}
