package com.neuedu.test;

import com.neuedu.dao.CartDao;
import com.neuedu.dao.mabaits.CartMybaits;
import com.neuedu.entity.Cart;
import com.neuedu.entity.PageModel;
import org.junit.Test;

import java.util.List;

public class CartTest {
    @Test
    public   void   testallCart(){
        CartDao   cartDao=new CartMybaits();
          List<Cart> mm= cartDao.findAllCart();
        for (Cart A:mm
             ) {
            System.out.println(A);

        }
    }

    @Test
    public  void addcart(){
        CartDao   cartDao=new CartMybaits();
         Cart cart=new Cart();
        cart.setProductid(1);
        cart.setProductNum(147852);
        cartDao.addCart(cart);
    }

    @Test
    public  void deletecart(){
        CartDao   cartDao=new CartMybaits();
        cartDao.deleteCart(89);
    }
    @Test
    public  void updatecart(){
        CartDao   cartDao=new CartMybaits();
        Cart Cart=new Cart();
        Cart.setId(78);
        Cart.setProductNum(1122222);
        cartDao.updataeCart(Cart);
    }
    @Test
    public  void updatecart1(){
        CartDao   cartDao=new CartMybaits();
        cartDao.updateCart(78,1);
    }
    @Test
    public  void findAllCartBYid(){
        CartDao   cartDao=new CartMybaits();
        System.out.println(cartDao.findcartById(76));
    }

    @Test
    public  void findCartBYpage(){
        CartDao   cartDao=new CartMybaits();
        PageModel<Cart> cart= cartDao.findCartByPage(1,2);
        for (Cart  s: cart.getData()
             ) {
            System.out.println(s);

        }
    }

    //应该返回集合大的bug虽然很简单，有时间再改
    @Test
    public  void  findcartByproductid(){
        CartDao   cartDao=new CartMybaits();
        System.out.println( cartDao.findcartByproductid(81));
    }

}