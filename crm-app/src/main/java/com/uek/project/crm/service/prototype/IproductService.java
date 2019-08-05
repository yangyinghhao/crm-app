package com.uek.project.crm.service.prototype;

import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.uek.project.crm.entity.Product;

public interface IproductService {
	void sreateProduct(Product product);
	List<Product> getAllProducts();
	
	Page<Product> getproducts(int pagenum,int pageSize);
}
