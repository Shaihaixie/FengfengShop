package com.neuedu.test;

import com.neuedu.dao.OrderDao;
import com.neuedu.dao.mabaits.OrderMybaits;
import com.neuedu.entity.PageModel;
import com.neuedu.entity.UserOrder;
import org.junit.Test;

import java.util.List;

public class OrderTest {
    @Test
    public  void findOrder(){
        OrderDao  OrderDao=new OrderMybaits();
            List<UserOrder>  lists=OrderDao.findAllorder();
        for (UserOrder m:lists
             ) {
            System.out.println(m);
        }
    }
     @Test
    public  void findOrderByPage(){
         OrderDao  OrderDao=new OrderMybaits();
        PageModel<UserOrder> lists  =  OrderDao.findUserOrderByPage(2,5);
         for (UserOrder m:lists.getData()
                 ) {
             System.out.println(m);
         }
     }
 @Test
    public  void  testCreatOrder(){
     OrderDao  OrderDao=new OrderMybaits();
     UserOrder UserOrder=new UserOrder();
    // order_no,user_id,shipping_id,payment,payment_type,postage,status
             UserOrder.setOrder_no(90);
             UserOrder.setUser_id(10);
           UserOrder.setPayment(1000);
      OrderDao.createOrder(UserOrder);
 }

}
