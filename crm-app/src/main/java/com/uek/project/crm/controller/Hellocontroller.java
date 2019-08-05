 package com.uek.project.crm.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import com.github.pagehelper.PageHelper;
import com.uek.project.crm.dao.ProductDao;
import com.uek.project.crm.entity.Product;
import com.uek.project.crm.service.impl.productServiceImpl;
import com.uek.project.crm.service.prototype.IproductService;

@Controller
public class Hellocontroller {
	private static final String FILE_DIR = null;
	@Autowired
	private IproductService productService;
	@Autowired
	private productServiceImpl pservice;
	@Autowired
	private ProductDao pdao;

	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "hello crm";
	}

	@GetMapping("/index")
	public ModelAndView index(int page) {

		ModelAndView mv = new ModelAndView();
		System.out.println(page);
		PageHelper.startPage(page, 5);
		List<Product> products = productService.getproducts(page, 5);
		// mv.addObject(attributeValue)
		mv.addObject("page", page);// 添加到html标签
		mv.addObject("products", products);
		mv.setViewName("index");
		return mv;
	}

	@GetMapping("/demo")
	// @RequestMapping(name="/demo",method=requ)
	public String demo() {
		return "demo";
	}

	@RequestMapping(value = "/saveproduct", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String sa(Product p) {

		pservice.sreateProduct(p);
		return "{\"0\":0}";
	}

	@RequestMapping(value = "/delete", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String satr(int id) {

		pdao.delete(id);
		return "{\"0\":0}";
	}

	@RequestMapping(value = "/selectpro", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String sat(int id) {

		pdao.select(id);

		return "{\"0\":0}";
	}

	@RequestMapping(value = "/select", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String satrr(Product p) {

		pdao.update(p);

		return "{\"0\":0}";
	}

	@GetMapping("toupload")
	public ModelAndView toUpload() {
		ModelAndView mv = new ModelAndView();
		// -- 获取所有的分类
		File allCat = new File("D:/upload/");
		String[] ac = allCat.list();
		mv.addObject("categories", ac);
		mv.setViewName("upload");
		return mv;
	
	}

	@PostMapping("/upload")
	@ResponseBody
	public String uploadFile(String apple,@RequestParam("file") MultipartFile[] files) throws Exception {
		System.out.println(files);
		for (MultipartFile file : files) {
			File f = new File("D:/upload/"+apple+"/"+ file.getOriginalFilename());
			if (!f.getParentFile().exists()) {
				f.getParentFile().mkdirs();
			}
			file.transferTo(f);
		}
		return "{'upload':'ok'}";
	}


	@GetMapping("/list")
	public ModelAndView list() {

		ModelAndView mv = new ModelAndView();

		File allcat = new File("D:/upload");

		String[] ac = allcat.list();

		mv.addObject("categories", ac);

		mv.setViewName("list");

		return mv;
	}
	@GetMapping("/list/{category}")
	@ResponseBody
	public String list(@PathVariable String category) {
		File cf = new File("D:/upload/" + category);
		String[] files = cf.list();

		// --获取所有的分类
		
		

		JSONObject jsonObject = new JSONObject();

		
		jsonObject.put("files", files);
		return jsonObject.toJSONString();
	}
	@GetMapping("/download/{category}/{file}")
	public void download(@PathVariable("category") String category, @PathVariable("file") String file,
			HttpServletResponse response) throws Exception {
		File f = new File("D:/upload/" + category + "/" + file);
		//-- 设置响应类型
		response.setContentType("application/force-dowmload");
		//-- 设置响应文件名
		response.addHeader("Content-Disposition", "attachment;fileName="+f.getName());
		// -- 文件输入流对象
		FileInputStream fis = new FileInputStream(f);
		// -- 文件缓存流对象
		BufferedInputStream bis = new BufferedInputStream(fis);
		// -- 文件输出流对象
		OutputStream os = response.getOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = bis.read(buffer)) != -1) {
			os.write(buffer, 0, len);
		}
		bis.close();
		fis.close();

	}
}
