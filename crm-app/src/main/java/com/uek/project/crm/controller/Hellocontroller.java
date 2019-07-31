package com.uek.project.crm.controller;

import java.io.File;
import java.util.List;

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
		List<Product> products = productService.getAllProducts();
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

	@GetMapping("/toupload")
	public String toUpload() {
		return "upload";
	}

	@PostMapping("/toupload")
	@ResponseBody
	public String up(@RequestParam("file") MultipartFile[] files) throws Exception {
		for (MultipartFile file : files) {
			file.transferTo(new File("D:/aaa/" + file.getOriginalFilename()));
		}
		return "{'upload':'ok'}";
	}

	// @RequestMapping(value="/download",method = RequestMethod.GET)
	// public void download( HttpServletResponse response){
	// //要上传的文件名字
	// String fileName="com.seven.xuanshang.apk";
	// //通过文件的保存文件夹路径加上文件的名字来获得文件
	// File file=new File(FILE_DIR,fileName);
	// //当文件存在
	// if(file.exists()){
	// //首先设置响应的内容格式是force-download，那么你一旦点击下载按钮就会自动下载文件了
	// response.setContentType("application/force-download");
	// //通过设置头信息给文件命名，也即是，在前端，文件流被接受完还原成原文件的时候会以你传递的文件名来命名
	// response.addHeader("Content-Disposition",String.format("attachment;
	// filename=\"%s\"", file.getName()));
	// //进行读写操作
	// byte[]buffer=new byte[1024];
	// FileInputStream fis=null;
	// BufferedInputStream bis=null;
	// try{
	// fis=new FileInputStream(file);
	// bis=new BufferedInputStream(fis);
	// OutputStream os=response.getOutputStream();
	// //从源文件中读
	// int i=bis.read(buffer);
	// while(i!=-1){
	// //写到response的输出流中
	// os.write(buffer,0,i);
	// i=bis.read(buffer);
	// }
	// }catch (IOException e){
	// e.printStackTrace();
	// }finally {
	// //善后工作，关闭各种流
	// try {
	// if(bis!=null){
	// bis.close();
	// }
	// if(fis!=null){
	// fis.close();
	// }
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// }
	// }
	@GetMapping("/list")
	public ModelAndView list() {

		ModelAndView mv = new ModelAndView();

		File allcat = new File("D:/upload");

		String[] ac = allcat.list();

		mv.addObject("categories", ac);

		mv.setViewName("list");

		return mv;
	}

	@ResponseBody
	public String list(@PathVariable String category) {
		File cf = new File("D:/upload/" + category);
		String[] files = cf.list();

		// --获取所有的分类
		File allCat = new File("D:/upload");
		String[] ac = allCat.list();

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("categories", ac);
		jsonObject.put("files", files);
		return jsonObject.toJSONString();
	}

}
