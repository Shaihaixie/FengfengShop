package com.neuedu.dao.mabaits;

import com.neuedu.dao.CartDao;
import com.neuedu.entity.Cart;
import com.neuedu.entity.PageModel;
import com.neuedu.utils.MyBatisFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartMybaits   implements CartDao {
    @Override
    public boolean addCart(Cart cart) {
        SqlSession session  = MyBatisFactory.getSqlSession();
        Map<String, String> map = new HashMap<String, String>();
        map.put("productid", cart.getProductid()+"");
        map.put("productnum", cart.getProductNum()+"");
         session.insert("com.neuedu.entity.Cart.addCart",map);
        return true;
    }

    @Override
    public boolean deleteCart(int id) {
            SqlSession  session=     MyBatisFactory.getSqlSession();
            session.delete("com.neuedu.entity.Cart.deleteCart",id);
            return true;
    }

    @Override
    public boolean updataeCart(Cart cart) {
        SqlSession  session=     MyBatisFactory.getSqlSession();
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", cart.getId()+"");
        map.put("productnum", cart.getProductNum()+"");
        session.update("com.neuedu.entity.Cart.updateCart",map);
        return true;
    }

    @Override
    public Cart findcartById(int id) {
        SqlSession  session=     MyBatisFactory.getSqlSession();
        Map<String, String> map = new HashMap<String, String>();
        map.put("cartid", id+"");
        map.put("id", id+"");
        return  session.selectOne("com.neuedu.entity.Cart.findAllCartBYid",map);
    }
//遍历所有的购物车
    @Override
    public List<Cart> findAllCart() {
        SqlSession session  = MyBatisFactory.getSqlSession();
      List<Cart> carts=  session.selectList("com.neuedu.entity.Cart.findAllCart");
        return carts;
    }

    @Override
    public int getCartNum() {
        return 0;
    }

    @Override
    public boolean updateCart(int id, int num) {
        SqlSession  session=     MyBatisFactory.getSqlSession();
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id+"");
        map.put("productnum", num+"");
        session.update("com.neuedu.entity.Cart.updateCart",map);
        return true;
    }

    @Override
    public void clearCart() {

    }

    @Override
    public PageModel<Cart> findCartByPage(int pageNo, int pageSize) {
//step1:查询总记录数，计算总共页数
        SqlSession session  = MyBatisFactory.getSqlSession();
        int    totalcount= (Integer) session.selectOne("com.neuedu.entity.Cart.findTotalCount");
        //step2：查询某页数据
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("offest",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        List<Cart> Carts= session.selectList("com.neuedu.entity.Cart.findCartPage",map);
        for (Cart s: Carts
             ) { s.setTotal(s.getProductNum()*s.getProduct().getPrice());
        }
        PageModel<Cart> pageModel=new PageModel<Cart>();
        pageModel.setTotalPage(((totalcount%pageSize)==0?totalcount/pageSize:(totalcount/pageSize)+1));
        pageModel.setData(Carts);
        return pageModel;
    }
//有bug下面应该是list
    @Override
    public Cart findcartByproductid(int productid) {
        SqlSession  session=     MyBatisFactory.getSqlSession();
             return       session.selectOne("com.neuedu.entity.Cart.findcartByproductid",productid);

    }

}
