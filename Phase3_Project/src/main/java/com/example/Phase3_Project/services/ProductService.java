package com.example.Phase3_Project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Phase3_Project.model.Product;
import com.example.Phase3_Project.respository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	public List<Product> getAllproducts(){
		return productRepository.findAll();
	}
	
	public void saveProduct(Product product) {
		productRepository.save(product);
	}
	
	public Product getProductById(Long id) {
		return productRepository.findById(id).orElse(null);
	}
	
	public void deleteProductById(Long id) {
		productRepository.deleteById(id);
	}
	
	public List<Product> getProductsByCategory(String category){
		return productRepository.findProductsByCategory(category);
		
	}
	
	public List<String> getAllCategories(){
		return productRepository.findAllCategories();
	}
	

}
