package com.neuedu.dao.mabaits;
import com.neuedu.dao.OrderItemDao;
import com.neuedu.entity.PageModel;
import com.neuedu.entity.UserOrder;
import com.neuedu.entity.UserOrderItem;
import com.neuedu.utils.MyBatisFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderItemMybatis implements OrderItemDao {
    @Autowired
    private      SqlSession sqlSession;
    @Override
    public boolean addOrderItems(List<UserOrderItem> orederItems) {
        //SqlSession session  = MyBatisFactory.getSqlSession();
        sqlSession.selectList("com.neuedu.entity.UserOrderItem.addOrderItems",orederItems);
        return  true;
    }
    @Override
    public int generateOrderItemId() {
        return 0;
    }

    @Override
    public List<UserOrderItem> findorder(long NO) {
        return  sqlSession.selectList("com.neuedu.entity.UserOrderItem.findorderitem",NO);
    }


}
