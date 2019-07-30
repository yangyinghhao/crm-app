package com.uek.project.crm;



import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.pagehelper.PageHelper;
import com.uek.project.crm.dao.ProductDao;
import com.uek.project.crm.entity.Product;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CrmAppApplicationTests {
	@Autowired
	private ProductDao ProdDao;
	
	@Test
	public void contextLoads() {
		for (int i = 0; i <=10; i++) {
			ProdDao.save(new Product("ipad"+i,i*100));
			
		}
	}
	
	@Test
	public void findproduct(){
		PageHelper.startPage(1,5);
		List<Product>prods=ProdDao.findall();
		for (Product product : prods) {
			System.out.println(product);
		}
	}
}                                              
