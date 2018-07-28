package com.neuedu.test;

import com.neuedu.dao.ProductDao;
import com.neuedu.dao.mabaits.ProductMybaits;
import com.neuedu.entity.PageModel;
import com.neuedu.entity.Product;
import org.junit.Test;

import java.util.List;

public class ProductTest {
    @Test
    public    void   testfindAll(){
        ProductDao  ProductDao=new ProductMybaits();
              List<Product>  products= ProductDao.findAll();
        for (Product  i: products) {
            System.out.println(i);
        }
    }

    @Test
    public    void   testfindbypage(){
        ProductDao  ProductDao=new ProductMybaits();
       PageModel<Product>  pagemodel = ProductDao.findProductByPage(1,2);
         System.out.println(pagemodel.getData().size());
    }
      @Test
    public    void   testinsertproduct(){
        ProductDao  ProductDao=new ProductMybaits();
        Product product=new  Product("8848","hao",33.3,"4444","sss",100);
        System.out.println( ProductDao.addProduct(product));
    }
    @Test
    public    void   testdeleteproductByid(){
        ProductDao  ProductDao=new ProductMybaits();
        System.out.println(  ProductDao.deleteProduct(91));

    }


}
