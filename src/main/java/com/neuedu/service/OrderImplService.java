package com.neuedu.service;

import com.neuedu.entity.PageModel;
import com.neuedu.entity.UserOrder;
import com.neuedu.entity.UserOrderItem;

import java.util.List;

public interface OrderImplService {
    List<UserOrderItem> findOrder(long no);
}
