package com.neuedu.dao.mabaits;

import com.neuedu.dao.CartDao;
import com.neuedu.dao.CategoryDao;
import com.neuedu.entity.Cart;
import com.neuedu.entity.Category;
import com.neuedu.entity.PageModel;
import com.neuedu.utils.MyBatisFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryMybaits implements CategoryDao {
    @Override
    public boolean addCategory(Category category) {
        SqlSession session  = MyBatisFactory.getSqlSession();
        session.insert("com.neuedu.entity.Category.addCategory",category);
        return true;
    }

    @Override
    public List<Category> findAll() {
        SqlSession session  = MyBatisFactory.getSqlSession();
        List<Category> list=  session.selectList("com.neuedu.entity.Category.findall");
        return list;
    }
    @Override
    public boolean updateCategory(Category category) {
        SqlSession session  = MyBatisFactory.getSqlSession();
        Map<Object,Object> map=new HashMap<Object,Object>();
      //  parent_id=?,cname=?,status=?,sort_order=?,create_time=?,update_time=? where id=?
        map.put("parent_id",category.getParent_id());
        map.put("cname",category.getCname());
        map.put("status",category.getStatus());
        map.put("sort_order",category.getSort_order());
        map.put("create_time",category.getCreate_time());
        map.put("update_time",category.getUpdate_time());
        map.put("id",category.getId());
     session.update("com.neuedu.entity.Category.updateCategory",map);
        return true;
    }

    @Override
    public boolean deleteCategory(int id) {
        SqlSession session  = MyBatisFactory.getSqlSession();
        session.delete("com.neuedu.entity.Category.deleteCategory",id);
        return true;
    }

    @Override
    public Category findById(int id) {
        SqlSession session  = MyBatisFactory.getSqlSession();
        Category      Category=session.selectOne("com.neuedu.entity.Category.findById",id);
        return Category;
    }

    @Override
    public PageModel<Category> findCategoryByPage(int pageNo, int pageSize) {
        //step1:查询总记录数，计算总共页数
        SqlSession session  = MyBatisFactory.getSqlSession();
        int    totalcount= (Integer) session.selectOne("com.neuedu.entity.Category.findTotalCount");
        //step2：查询某页数据
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("offest",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        List<Category> Categorys= session.selectList("com.neuedu.entity.Category.findcategoryPage",map);
        PageModel<Category> pageModel=new PageModel<Category>();
        pageModel.setTotalPage(((totalcount%pageSize)==0?totalcount/pageSize:(totalcount/pageSize)+1));
        pageModel.setData(Categorys);
        return pageModel;
    }

    @Override
    public List<Category> findAllparentname() {
        SqlSession session  = MyBatisFactory.getSqlSession();
        List<Category> list=  session.selectList("com.neuedu.entity.Category.findAllparentname");
        return list;
    }

    @Override
    public List<Category> findAllsonname(String parentname) {
        SqlSession session  = MyBatisFactory.getSqlSession();
        List<Category> list=      session.selectList("com.neuedu.entity.Category.findAllsonname",parentname);
        return list;
    }
}
