package com.neuedu.service.impl;

import java.util.List;

import com.neuedu.dao.CategoryDao;
import com.neuedu.dao.ProductDao;
import com.neuedu.dao.impl.jdbc.CategoryDaoImpl;
import com.neuedu.dao.mabaits.CategoryMybaits;
import com.neuedu.entity.Category;
import com.neuedu.entity.PageModel;
import com.neuedu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cService")
public class CategoryServiceImpl implements CategoryService {
//	CategoryDao categoryDao=new CategoryDaoImpl();
@Autowired
CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public boolean addCategory(Category category) {
		// TODO Auto-generated method stub
		return categoryDao.addCategory(category);
	}

	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return categoryDao.findAll();
	}

	@Override
	public boolean updateCategory(Category category) {
		// TODO Auto-generated method stub
		return categoryDao.updateCategory(category);
	}

	@Override
	public boolean deleteCategory(int id) {
		// TODO Auto-generated method stub
		return categoryDao.deleteCategory(id);
	}

	@Override
	public Category findById(int id) {
		// TODO Auto-generated method stub
		return categoryDao.findById(id);
	}

	@Override
	public PageModel<Category> findCategoryByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return categoryDao.findCategoryByPage(pageNo, pageSize);
	}

	@Override
	public List<Category> findAllparentname() {
		// TODO Auto-generated method stub
		return categoryDao.findAllparentname();
	}

	@Override
	public List<Category> findAllsonname(String sonname) {
		// TODO Auto-generated method stub
		return categoryDao.findAllsonname(sonname);
	}

}
