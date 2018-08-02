package com.neuedu.dao;

import java.util.List;

import com.neuedu.entity.Cart;
import com.neuedu.entity.Category;
import com.neuedu.entity.PageModel;
import com.neuedu.entity.Product;

public interface CartDao {

	/*
	* 添加购物车
	* */

	boolean  addCart(Cart cart);
	/*
	 * 删除购物车
	 * */
	boolean  deleteCart(int id);
	/*
	 * 修改购物车
	 * */
	boolean  updataeCart(Cart cart);
	/*
	 * 查购物车BYid
	 * */
	  Cart  findcartById(int id);
	/*
	 * 遍历购物车
	 * */
	List<Cart> findAllCart();

	/*
	 * 购物车数量
	 * */
	int  getCartNum();

	/*
	 * 修改购物车
	 * */
    	boolean  updateCart(int id, int num);


	/*
	 * 删除购物车
	 * */
	   void   clearCart() ;
	/*
	 * 分页查购物车
	 * */
	public  PageModel<Cart> findCartByPage(int pageNo, int pageSize);
	Cart findcartByproductid(int productid);
	
	
}
