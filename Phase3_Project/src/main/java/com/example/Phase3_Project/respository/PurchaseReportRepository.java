package com.example.Phase3_Project.respository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Phase3_Project.model.PurchaseReport;

public interface PurchaseReportRepository extends JpaRepository<PurchaseReport, Long> {
	
	@Query("select pr from PurchaseReport pr " + 
			"Where pr.purchaseDate between ?1 and ?2 " + 
			"and pr.product.category =?3"			)
	List<PurchaseReport> findByPurchaseDateBetweenAndCategory(Date startDate, Date endDate, String category);

}
