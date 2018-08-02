package com.neuedu.test;
import com.neuedu.entity.UserOrder;
import com.neuedu.service.OrderService;
import com.neuedu.service.impl.OrderServiceImpl;
import org.junit.Test;
public class  OrderitemTest {
      @Test
    public  void addOrdritem(){
          OrderService a=new OrderServiceImpl();
          System.out.println(a.createOrder());
      }
}
