package com.neuedu.test;

import com.neuedu.dao.CategoryDao;
import com.neuedu.dao.mabaits.CategoryMybaits;
import com.neuedu.entity.Category;
import com.neuedu.entity.PageModel;
import org.junit.Test;

import java.util.List;

public class CategoryTest {
    @Test
    public  void      testFindAll(){
        CategoryDao CategoryDao=new CategoryMybaits();
          List<Category>     list=  CategoryDao.findAll();
        for (Category a:list
             ) {
            System.out.println(a.getCname());
        }
    }
    @Test
    public  void  testaddCategory(){
        CategoryDao CategoryDao=new CategoryMybaits();
        Category Category=new Category();
        //id,cname,status,sort_order,create_time,update_time
        Category.setId(50);
        Category.setCname("小猪佩奇");
        CategoryDao.addCategory(Category);
    }
    @Test
    public  void testUpdateCategory(){
        CategoryDao CategoryDao=new CategoryMybaits();
        Category Category=new Category();
        //id,cname,status,sort_order,create_time,update_time
        Category.setId(50);
        Category.setCname("555356");
        CategoryDao.updateCategory(Category);
    }
    @Test
    public  void testdeleteCategory(){
        CategoryDao CategoryDao=new CategoryMybaits();
        CategoryDao.deleteCategory(50);
    }
  @Test
    public  void testfFindByid(){
      CategoryDao CategoryDao=new CategoryMybaits();
      System.out.println(CategoryDao.findById(50).getCname());
  }
    @Test
    public  void testfparentname(){
        CategoryDao CategoryDao=new CategoryMybaits();
       List<Category>   list=CategoryDao.findAllparentname();
        for (Category a:list
             ) {
            System.out.println(a.getCname());

        }
    }
    @Test
    public  void  findAllsonname(){
        CategoryDao CategoryDao=new CategoryMybaits();
        List<Category>   list=CategoryDao.findAllsonname("shouji");
        for (Category a:list
                ) {
            System.out.println(a.getCname());

        }
    }
    @Test
    public  void  testfindCategoryBypage(){
        CategoryDao CategoryDao=new CategoryMybaits();
         PageModel<Category> List= CategoryDao.findCategoryByPage(1,5);
        for (Category a:List.getData()
             ) {
            System.out.println(a.getCname());
        }
    }
}
