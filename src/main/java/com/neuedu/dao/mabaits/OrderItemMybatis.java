package com.neuedu.dao.mabaits;
import com.neuedu.dao.OrderItemDao;
import com.neuedu.entity.UserOrderItem;
import com.neuedu.utils.MyBatisFactory;
import org.apache.ibatis.session.SqlSession;
import java.util.List;
public class OrderItemMybatis implements OrderItemDao {
    @Override
    public boolean addOrderItems(List<UserOrderItem> orederItems) {
        SqlSession session  = MyBatisFactory.getSqlSession();
        session.selectList("com.neuedu.entity.UserOrderItem.addOrderItems",orederItems);
        return  false;
    }
    @Override
    public int generateOrderItemId() {
        return 0;
    }
}
