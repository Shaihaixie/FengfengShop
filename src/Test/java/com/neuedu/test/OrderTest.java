package com.neuedu.test;

import com.neuedu.dao.OrderDao;
import com.neuedu.dao.mabaits.OrderMybaits;
import com.neuedu.entity.PageModel;
import com.neuedu.entity.UserOrder;
import com.neuedu.service.OrderService;
import com.neuedu.service.impl.OrderServiceImpl;
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
     OrderService A=new OrderServiceImpl();

//        A.createOrder();
 }
    @Test
    public  void  testfindOrderByOrderno(){
        OrderDao OrderDao=new OrderMybaits();
        UserOrder UserOrder=   OrderDao.findOrderByOrderno(1533035453924L);

             System.out.println(UserOrder);


    }


}
