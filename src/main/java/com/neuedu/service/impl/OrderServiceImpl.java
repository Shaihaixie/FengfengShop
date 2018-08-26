package com.neuedu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.neuedu.dao.CartDao;
import com.neuedu.dao.OrderDao;
import com.neuedu.dao.OrderItemDao;
import com.neuedu.dao.ProductDao;
import com.neuedu.dao.impl.jdbc.CartDaoImpl;
import com.neuedu.dao.impl.jdbc.OrderDaoImpl;
import com.neuedu.dao.impl.jdbc.OrderItemDaoImpl;
import com.neuedu.dao.impl.jdbc.ProductDaoImpl;
import com.neuedu.dao.mabaits.CartMybaits;
import com.neuedu.dao.mabaits.OrderItemMybatis;
import com.neuedu.dao.mabaits.OrderMybaits;
import com.neuedu.dao.mabaits.ProductMybaits;
import com.neuedu.entity.Cart;
import com.neuedu.entity.PageModel;
import com.neuedu.entity.Product;
import com.neuedu.entity.UserOrder;
import com.neuedu.entity.UserOrderItem;
import com.neuedu.service.OrderService;
import com.neuedu.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
//OrderDao orderDao=new OrderDaoImpl();
@Autowired
OrderDao orderDao;
	@Autowired
	CartDao CartDao;
	@Autowired
	OrderItemDao OrderItemDao;
	@Autowired
	ProductDao ProductDao;
//OrderDao orderDao=new OrderMybaits();
//CartDao CartDao=new CartMybaits();
//OrderItemDao OrderItemDao=new OrderItemMybatis();
//ProductDao ProductDao=new ProductMybaits();
	@Override
	public boolean createOrder(UserOrder userOrder) {
		return false;
	}
	@Override
	public boolean createOrder() {
		// TODO Auto-generated method stub
//		step1:��ȡ���ﳵ�еĹ�����Ϣ  List<Cart>
		List<Cart> carts= CartDao.findAllCart();
		 if(carts==null||carts.size()<=0) {
			 return false;
		 }
		//step2:���ɶ���ʵ���� UserOrder
		   UserOrder   UserOrder=createUserOrder();
		//step3:��������Ϣ����ת�ɶ�����ϸ����List<UserOrderItem>
		  List<UserOrderItem> orderItems=new ArrayList<UserOrderItem>();
 		   for(int i=0;i<carts.size();i++) {
 			   Cart cart=carts.get(i);
 			   UserOrderItem orderItem= Utils.convertToOrderItem(OrderItemDao.generateOrderItemId(), UserOrder.getOrder_no(), cart);
 			  //step4:������
 			   if(orderItem.getQuantity()<=cart.getProduct().getStock()) {
 				   //������
 				  orderItems.add(orderItem);
 			   }else {//��治��
 				  return false;
 			   }
 		   }
		 //step5:���㶩���۸�
		UserOrder.setPayment(getOrderPrice(orderItems));
		//step5:�µ�
 		   orderDao.createOrder(UserOrder);
		OrderItemDao.addOrderItems(orderItems);
		//step6:�ۿ��
 		   for(int  i=0;i<carts.size();i++) {
 			   Cart cart=carts.get(i);
 			   Product product=cart.getProduct();
 			   int leftStock=product.getStock()-cart.getProductNum();
 			 //�޸Ŀ��
			   product.setStock(leftStock);
			   ProductDao.updateProduct(product);
 		   }
		//step7:��չ��ﳵ
		CartDao.clearCart();
	return orderDao.createOrder(UserOrder);
	}
	
	
	/**
	 * ���ɶ�������
	 * */
	public  UserOrder createUserOrder() {
		
		UserOrder order=new UserOrder();
		//���ö���id
		order.setId(orderDao.generateOrderId());
		// 1s=1000ms  1970 1.1 - ��ǰ
		order.setOrder_no(generateOrderNo());
//		order.setCreate_time(System.currentTimeMillis());
		
		return order;
	}

	/**
	 * ���ɶ�����
	 * */
	
	@Override
	public long generateOrderNo() {
		// TODO Auto-generated method stub
		return System.currentTimeMillis();
	}
	
	/**
	 * ���㶩���۸�
	 * */
	public  double  getOrderPrice(List<UserOrderItem> items) {
		double  totalPrice=0.0;
		for(int i=0;i<items.size();i++) {
			totalPrice+=items.get(i).getTotal_price();
		}
		return  totalPrice;
	}


	@Override
	public List<UserOrder> findAllorder() {
		// TODO Auto-generated method stub
		return orderDao.findAllorder();
	}


	@Override
	public PageModel<UserOrder> findOrderByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return orderDao.findUserOrderByPage(pageNo, pageSize);
	}
	

}
