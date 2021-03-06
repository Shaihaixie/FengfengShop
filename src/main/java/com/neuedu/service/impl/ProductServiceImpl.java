package com.neuedu.service.impl;

import java.util.List;

import com.neuedu.dao.ProductDao;
import com.neuedu.dao.impl.jdbc.ProductDaoImpl;
import com.neuedu.dao.mabaits.ProductMybaits;
import com.neuedu.entity.PageModel;
import com.neuedu.entity.Product;
import com.neuedu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("pService")
public class ProductServiceImpl implements ProductService {

	
//	ProductDao productDao=new ProductDaoImpl();
@Autowired
	ProductDao productDao;
		//=new ProductMybaits();
	@Override
	public boolean addProduct(Product product) {
		// TODO Auto-generated method stub
		
		return productDao.addProduct(product);
	}

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return productDao.findAll();
	}

	@Override
	public List<Product> findAll1() {
		return productDao.findAll1();
	}

	@Override
	public boolean updateProduct(Product product) {
		// TODO Auto-generated method stub
		return productDao.updateProduct(product);
	}

	@Override
	public boolean deleteProduct(int id) {
		// TODO Auto-generated method stub
		return productDao.deleteProduct(id);
	}

	@Override
	public Product findProductById(int id) {
		// TODO Auto-generated method stub
		return productDao.findById(id);
	}

	@Override
	public PageModel<Product> findProductByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return productDao.findProductByPage(pageNo, pageSize);
	}

	@Override
	public List<Product> findBycategory_id(int category_id) {
		// TODO Auto-generated method stub
		return productDao.findBycategory_id(category_id);
	}

	@Override
	public boolean updateStock(Product product) {
		// TODO Auto-generated method stub
		return productDao.updateStock(product);
	}

	@Override
	public List<Product> findAllorder() {
		// TODO Auto-generated method stub
		return productDao.findAllorder();
	}

	
}
