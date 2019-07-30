package com.uek.project.crm.dao;



import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.Alias;

import com.uek.project.crm.entity.Product;

public interface ProductDao {
	@Select("select *from t_product")
	List<Product>findall();
//	@Insert("insert into t_product(name,price)values(#{name},#{price})")
	void save(Product product);
	@Delete("delete from t_product where id =#{id}")
	void  delete(int id);
	@Update("update t_product set name=#{name},price=#{price} where id=#{id}")
	void update(Product p);
	@Select("select * from t_product where id =#{id}")
	Product select(int id);
}
