package com.uek.project.crm.service.impl;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.uek.project.crm.dao.ProductDao;
import com.uek.project.crm.entity.Product;
import com.uek.project.crm.service.prototype.IproductService;
@Service
public class productServiceImpl implements IproductService{
	@Autowired
	private ProductDao ProductDao;
	@Autowired RedisTemplate<Object, Object> rt;
	@Override
	public void sreateProduct(Product product) {
		ProductDao.save(product);
		
	}

	@Override   //--做缓存
	@Cacheable(value="products")
	public List<Product> getAllProducts() {
//		List<Product> ps =null;
//		ps =(List<Product>) rt.opsForValue().get("safasfasfasfasfasfa");
//		if (ps==null) {
//			System.out.println("cache data first.");
//			ps=ProductDao.findall();
//			rt.opsForValue().set("safasfasfasfasfasfa",ps,Duration.ofSeconds(3600));
//		}
//		return ps;
		return ProductDao.findall();
	}

	@Override
	@Cacheable(value="products",key="#pageNum")
	public Page<Product> getproducts(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		Page<Product> page=(Page<Product>) ProductDao.findall();
		return page;
	}

	

	

	
	
	
	

}
