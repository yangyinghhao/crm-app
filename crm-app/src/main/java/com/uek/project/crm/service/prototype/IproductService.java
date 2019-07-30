package com.uek.project.crm.service.prototype;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.uek.project.crm.entity.Product;

public interface IproductService {
	void sreateProduct(Product product);
	List<Product> getAllProducts();
	void  startPage(int a,int b);
	int page(int page);
	
}
