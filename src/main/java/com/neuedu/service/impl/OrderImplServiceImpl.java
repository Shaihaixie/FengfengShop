package com.neuedu.service.impl;

import com.neuedu.dao.OrderItemDao;
import com.neuedu.entity.PageModel;
import com.neuedu.entity.UserOrder;
import com.neuedu.entity.UserOrderItem;
import com.neuedu.service.OrderImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("OrderImplService")
public class OrderImplServiceImpl  implements OrderImplService{
    @Autowired
    OrderItemDao OrderItemDao;
    @Override
    public List<UserOrderItem> findOrder(long no) {
        return  OrderItemDao.findorder(no);
    }
}
