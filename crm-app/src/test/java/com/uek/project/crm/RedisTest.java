package com.uek.project.crm;



import java.util.List;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.uek.project.crm.entity.Product;
import com.uek.project.crm.service.impl.productServiceImpl;



@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
	@Autowired
	private StringRedisTemplate srt;
	@Autowired
	private RedisTemplate<Object, Object> rt;
	@Autowired
	private productServiceImpl productServiceImpl;
	public void test01(){
		srt.opsForValue().set("javaee","unjf-1903");
	}
	@Test
	public void test(){
		String value =srt.opsForValue().get("javaee");
		System.out.println(value);
	}
	@Test
	public void test02(){
		Product p =new Product();
		p.setId(1);
		p.setName("笔记本");
		p.setPrice(2000);
		rt.opsForValue().set("product", p);
	}
	@Test
	public void test03(){
		System.out.println(1);
	}
	@Test
	public void test04(){
		List<Product> ps =productServiceImpl.getAllProducts();
		for (Product product :ps) {
			System.out.println(product);
		}
	}
}
