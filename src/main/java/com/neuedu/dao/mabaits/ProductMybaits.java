package com.neuedu.dao.mabaits;

import com.neuedu.dao.ProductDao;
import com.neuedu.entity.PageModel;
import com.neuedu.entity.Product;
import com.neuedu.utils.MyBatisFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductMybaits  implements ProductDao{
    @Override
    public boolean addProduct(Product product) {
        SqlSession session  = MyBatisFactory.getSqlSession();
        session.insert("com.neuedu.entity.Product.addproduct",product);
        return true;
    }


    //查找所有的商品
    @Override
    public List<Product> findAll() {
        SqlSession session  = MyBatisFactory.getSqlSession();

            List<Product>  list=session.selectList("com.neuedu.entity.Product.FindProduct");

            return list;


    }

    @Override
    public List<Product> findAllorder() {
        return null;
    }

    @Override
    public boolean updateProduct(Product product) {
        return false;
    }

    @Override
    public boolean deleteProduct(int id) {
        SqlSession session  = MyBatisFactory.getSqlSession();
        session.delete("com.neuedu.entity.Product.delectProductByid",id);
        return true;
    }

    @Override
    public Product findById(int id) {
        return null;
    }

    @Override
    public List<Product> findBycategory_id(int category_id) {
        return null;
    }

    @Override
    public PageModel<Product> findProductByPage(int pageNo, int pageSize) {
//step1:查询总记录数，计算总共页数
        SqlSession session  = MyBatisFactory.getSqlSession();
            int    totalcount= (Integer) session.selectOne("com.neuedu.entity.Product.findTotalCount");
        //step2：查询某页数据
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("offest",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
          List<Product> Products= session.selectList("com.neuedu.entity.Product.findProductPage",map);
              PageModel<Product> pageModel=new PageModel<Product>();
             pageModel.setTotalPage(((totalcount%pageSize)==0?totalcount/pageSize:(totalcount/pageSize)+1));
             pageModel.setData(Products);
        return pageModel;
    }

    @Override
    public boolean updateStock(Product product) {
        return false;
    }
}
