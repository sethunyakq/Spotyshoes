package com.example.Phase3_Project.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Phase3_Project.model.Product;
import com.example.Phase3_Project.model.PurchaseReport;
import com.example.Phase3_Project.model.User;
import com.example.Phase3_Project.services.ProductService;
import com.example.Phase3_Project.services.PurchaseReportService;
import com.example.Phase3_Project.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PurchaseReportService purchaseReportService;
	
	@GetMapping("/dashboard")
	public String adminDashboard(Model model) {
		return "admindashboard";
	}
	
	@GetMapping("/manageproducts")
	public String manageProducts(Model model) {
		List<Product> products = productService.getAllproducts();
		model.addAttribute("products", products);
		return "manageproducts";		
	}
	
	@GetMapping("/addproduct")
	public String showAddProductForm(Model model) {
		model.addAttribute("product", new Product());
		return "addproduct";
	}
	
	@PostMapping("/addproduct")
	public String addProduct(@ModelAttribute("product") Product product) {
		System.out.println("product added");
		productService.saveProduct(product);
		return "redirect:/admin/manageproducts";		
	}
	
	@GetMapping("/editproduct/{id}")
	public String showEditProductForm(@PathVariable("id") Long id, Model model) {
		Product product = productService.getProductById(id);
		
		if(product == null) {
			return "productnotfound";
		}
		model.addAttribute("product",product);
		return "editproduct";
	}
	
	@PostMapping("/editproduct")
	public String editProduct(@ModelAttribute("product") Product product) {
		productService.saveProduct(product);
		return "redirect:/admin/manageproducts";		
	}
	
	@GetMapping("/deleteproduct/{id}")
	public String deleteProduct(@PathVariable("id") Long id, Model model) {
		Product product = productService.getProductById(id);
		
		if(product == null) {
			return "productnotfound";
		}
		productService.deleteProductById(id);
		return "redirect:/admin/manageproducts";	
	}
	
	@GetMapping("/managecategories")
	public String manageCategories(Model model) {
		List<String> categories = productService.getAllCategories();
		model.addAttribute("categories",categories);
		return "managecategories";
	}
	
	@GetMapping("/productsbycategory/{category}")
	public String showProductsByCategory(@PathVariable("category") String category, Model model) {
		List<Product> products = productService.getProductsByCategory(category);
		model.addAttribute("products", products);
		return "productsbycategory";
		
	}
	
	
	
	@GetMapping("/manageusers")
	public String showManageUsersPage(Model model) {
		List<User> users = userService.getAllusers();
		model.addAttribute("users",users);
		return "manageusers";
	}
	
	@PostMapping("/searchUser")
	public String searchusers(@RequestParam("keyword") String keyword, Model model) {
		List<User> users = userService.searchUser(keyword);
		model.addAttribute("users", users);
		return "manageusers";
	}
	
	@GetMapping("/purchasereports")
	public String showPurchaseReportPage() {
		return "purchasereports";
	}
	
	@PostMapping("/purchasereports")
	public String filterPurchaseReports(@RequestParam("startDate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date startDate,
			@RequestParam("endDate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date endDate,
			@RequestParam("category") String category,
			Model model) {
		
		List<PurchaseReport> purchaseReports = purchaseReportService.getPurchaseReportsByDateAndCategory(startDate, endDate, category);
		model.addAttribute("purchaseReports",purchaseReports);
		return "purchasereports";
		
	}
			

}
