package com.neuedu.test;

import com.neuedu.dao.OrderDao;
import com.neuedu.dao.mabaits.OrderMybaits;
import com.neuedu.entity.PageModel;
import com.neuedu.entity.UserOrder;
import com.neuedu.service.OrderService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;
public class OrderTest {
    @Test
    public void findOrder() {
        OrderDao OrderDao = new OrderMybaits();
        List<UserOrder> lists = OrderDao.findAllorder();
        for (UserOrder m : lists
                ) {
            System.out.println(m);
        }
    }
    @Test
    public void findOrderByPage() {
        OrderDao OrderDao = new OrderMybaits();
        PageModel<UserOrder> lists = OrderDao.findUserOrderByPage(2, 5);
        for (UserOrder m : lists.getData()
                ) {
            System.out.println(m);
        }
    }

    @Test
    public void testCreatOrder() {

            ApplicationContext ApplicationContext = new ClassPathXmlApplicationContext("Spring-config.xml");
            OrderService orderService = ApplicationContext.getBean(OrderService.class);
            orderService.createOrder();
        }

        @Test
        public  void  testfindOrderByOrderno(){
        OrderDao OrderDao=new OrderMybaits();
        UserOrder UserOrder=   OrderDao.findOrderByOrderno(1533035453924L);
             System.out.println(UserOrder);


    }


    }
