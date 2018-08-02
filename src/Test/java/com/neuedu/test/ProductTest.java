package com.neuedu.test;

import com.neuedu.dao.ProductDao;
import com.neuedu.dao.impl.jdbc.ProductDaoImpl;
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
        System.out.println(  ProductDao.deleteProduct(95));

    }
    @Test
    public    void   testupdateProduct(){
         Product  product=new Product();
         product.setId(81);
         product.setName("小米12");
        ProductDao  ProductDao=new ProductMybaits();
         ProductDao.updateProduct(product);
    }

    @Test
    public    void   testFindProductByid(){
        ProductDao  ProductDao=new ProductMybaits();
        System.out.println(ProductDao.findById(96).getId());
    }
    @Test
    public    void   testFindBycategory_id(){
        ProductDao  ProductDao=new ProductMybaits();
        System.out.println(ProductDao.findBycategory_id(10));
    }
    @Test
    public    void   testFindByOder(){
        ProductDao  ProductDao=new ProductMybaits();
        System.out.println(ProductDao.findAllorder());
    }
    @Test
    public    void   testUpdateStock(){
        ProductDao  ProductDao=new ProductMybaits();
//        Product  product=ProductDao.findById(82);
        Product  product=new Product();
        product.setId(96);
         product.setStock(10);
        System.out.println(ProductDao.updateStock(product));
    }


}
