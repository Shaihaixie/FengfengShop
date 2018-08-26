package com.neuedu.dao.mabaits;

import com.neuedu.dao.OrderDao;
import com.neuedu.entity.PageModel;
import com.neuedu.entity.UserOrder;
import com.neuedu.utils.MyBatisFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class OrderMybaits implements OrderDao {
    @Autowired
    private      SqlSession sqlSession;
    @Override
    public PageModel<UserOrder> findUserOrderByPage(int pageNo, int pageSize) {
        // SqlSession session  = MyBatisFactory.getSqlSession();
        int    totalcount= (Integer) sqlSession.selectOne("com.neuedu.entity.UserOrder.findTotalCount");
        //step2：查询某页数据
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("offest",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        List<UserOrder> UserOrders= sqlSession.selectList("com.neuedu.entity.UserOrder.findUserorderPage",map);
        PageModel<UserOrder> pageModel=new PageModel<UserOrder>();
        pageModel.setTotalPage(((totalcount%pageSize)==0?totalcount/pageSize:(totalcount/pageSize)+1));
        pageModel.setData(UserOrders);
        return pageModel;
    }
    @Override
    public boolean createOrder(UserOrder userOrder) {
      //  SqlSession session  = MyBatisFactory.getSqlSession();
        sqlSession.insert("com.neuedu.entity.UserOrder.createOrder",userOrder);
        return true;
    }
    @Override
    public int generateOrderId() {
        return 0;
    }
    @Override
    public List<UserOrder> findAllorder() {
        //SqlSession session  = MyBatisFactory.getSqlSession();
         List<UserOrder>   userOrder=   sqlSession.selectList("com.neuedu.entity.UserOrder.findAllorder");
        return userOrder;
    }

    @Override
    public UserOrder findOrderByOrderno(long orderno) {
       // SqlSession session  = MyBatisFactory.getSqlSession();
    UserOrder UserOrder=   sqlSession.selectOne("com.neuedu.entity.UserOrder.findOrderByOrderno",orderno);
     return UserOrder;
    }
}
