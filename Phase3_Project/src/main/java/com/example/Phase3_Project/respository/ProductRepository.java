package com.example.Phase3_Project.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Phase3_Project.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Query("select p from Product p where p.category = :category")
	public List<Product> findProductsByCategory(String category);
	
	@Query("select distinct p.category from Product p")
	List<String> findAllCategories();
	

}
