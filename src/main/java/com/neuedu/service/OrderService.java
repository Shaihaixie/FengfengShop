package com.neuedu.service;

import java.util.List;

import com.neuedu.entity.Cart;
import com.neuedu.entity.PageModel;
import com.neuedu.entity.UserOrder;

public interface OrderService {

	/**
	 * �û��µ�
	 * */
	boolean  createOrder();
	boolean  createOrder(UserOrder userOrder);
	 List<UserOrder> findAllorder();
	/**
	 * 
	 * ���ɶ������order_no
	 * */
  long  generateOrderNo();
  /**��ҳ��ȡ*/
  public PageModel<UserOrder> findOrderByPage(int pageNo, int pageSize);
}
