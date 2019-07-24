package com.uek.project.crm.dao;



import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.Alias;

import com.uek.project.crm.entity.Product;

public interface ProductDao {
	@Select("select *from t_product")
	List<Product>findall();
//	@Insert("insert into t_product(name,price)values(#{name},#{price})")
	void save(Product product);
}
