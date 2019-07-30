package com.uek.project.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uek.project.crm.dao.ProductDao;
import com.uek.project.crm.entity.Product;
import com.uek.project.crm.service.prototype.IproductService;
@Service
public class productServiceImpl implements IproductService{
	@Autowired
	private ProductDao ProductDao;
	
	@Override
	public void sreateProduct(Product product) {
		ProductDao.save(product);
		
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return ProductDao.findall();
	}

	

	@Override
	public void startPage(int a, int b) {
		
		
	}

	@Override
	public int page(int page) {
		
		return page;
	}

	
	
	
	

}
