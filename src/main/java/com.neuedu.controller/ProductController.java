package com.neuedu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import com.neuedu.entity.PageModel;
import com.neuedu.entity.Product;
import com.neuedu.service.ProductService;
import com.neuedu.service.impl.ProductServiceImpl;

@WebServlet("/view/product")
public class ProductController extends HttpServlet {
	ProductService pService = new ProductServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		String operation = req.getParameter("operation");
		if (operation != null && !operation.equals("")) {
			if (operation.equals("1")) {
				addProduct(req, resp);
			} else if (operation.equals("2")) {
				findAll(req, resp);
			} else if (operation.equals("3")) {
				deleteProduct(req, resp);
			} else if (operation.equals("4")) {
				updateProduct(req, resp);
			} else if (operation.equals("5")) {
				findProductById(req, resp);
			}
		 else if (operation.equals("6")) {
			findProductBycageId(req, resp);
		  }
		else if (operation.equals("7")) {
			findAllorder(req, resp);
		}
	}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void addProduct(HttpServletRequest req, HttpServletResponse resp) {
		Product product = new Product();
		String name = req.getParameter("pname");
		String desc = req.getParameter("pdesc");
//		String image = req.getParameter("pimage");
//		String rule = req.getParameter("rule");
		double price = 0.0;
		int stock = 0;
		boolean result = false;
		try {
			price = Double.parseDouble(req.getParameter("price"));
//			stock = Integer.parseInt(req.getParameter("stock"));
			product.setName(name);
			product.setDesc(desc);
			product.setPrice(price);
//			product.setImage(image);
//			product.setRule(rule);
//			product.setStock(stock);
			result = addProduct(product);
//			findAll(req, resp);

		} catch (Exception e) {
			// TODO: handle exception
		}
		if (result) {
			System.out.println("添加成功");
		} else {
			System.out.println("添加失败");
		}

	}

	/** 娣诲姞鍟嗗搧 */
	public boolean addProduct(Product product) {
		return pService.addProduct(product);
	}

	public boolean updateProduct(Product product) {
		return pService.addProduct(product);
	}

	/**
	 * 鎵惧晢鍝�
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	public void findAllorder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		    
	        List<Product> products = pService.findAllorder();
	        String info=JSONArray.toJSONString(products);
	        resp.getWriter().print(info);
	}
	
	
	public void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		String  info= JSONArray.toJSONString(pageModel);
		 resp.getWriter().print(info);
		 req.setAttribute("pageModel", pageModel);
//        req.getRequestDispatcher("ShopList.jsp").forward(req, resp);
	}

	/** 淇敼鍟嗗搧 */
	public void updateProduct(HttpServletRequest req, HttpServletResponse resp) {
		Product product = pService.findProductById(Integer.parseInt(req.getParameter("id")));
		if (product.getName() != null) {
			String name = req.getParameter("name");
			String desc = req.getParameter("pdesc");
			String image = req.getParameter("pimage");
			String rule = req.getParameter("rule");
			double price = 0.0;
			int stock = 0;
			try {
				price = Double.parseDouble(req.getParameter("price"));
				stock = Integer.parseInt(req.getParameter("stock"));
				product.setName(name);
				product.setDesc(desc);
				product.setPrice(price);
				product.setImage(image);
				product.setRule(rule);
				product.setStock(stock);
				boolean flag = pService.updateProduct(product);
				if (flag) {
					System.out.println("鍟嗗搧淇敼鎴愬姛");
				findAll(req, resp);
				} else {
					System.out.println("鍟嗗搧淇敼澶辫触");
				}

			} catch (Exception e) {
				// TODO: handle exception
			}

		} else {
			System.out.println("鎵句笉鍒�");
		}
	}

	/**
	 * 鍒犻櫎鍟嗗搧
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */

	public void deleteProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean flag = pService.deleteProduct(Integer.parseInt(request.getParameter("id")));
		if (flag) {
			System.out.println("删除成功");
		   findAll(request, response);

		} else {
			System.out.println("删除失败");
		}
	}

	public void findProductById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sid = req.getParameter("id");
		int id = 0;
		try {
			id = Integer.parseInt(sid);
			Product products = pService.findProductById(id);
			if (products != null) {
				req.setAttribute("products", products);
				PrintWriter pw=resp.getWriter();
				String     callback=     req.getParameter("callback");
				Gson gson=new Gson();
				String json=gson.toJson(products);
				pw.write(callback+"("+json+")");
				System.out.println(json);
				//req.getRequestDispatcher("UpShopList.jsp").forward(req, resp);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public Product findProductById(int id) {
		return pService.findProductById(id);
	}

	public void findProductBycageId(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String sid = req.getParameter("id");
		int id = 0;
		try {
			id = Integer.parseInt(sid);
			List<Product> products = pService.findBycategory_id(id);
			req.setAttribute("products", products);
			req.getRequestDispatcher("shoptype.jsp").forward(req, resp);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public List<Product> findProductBycageId(int category_id) {
		return pService.findBycategory_id(category_id);
	}
}
