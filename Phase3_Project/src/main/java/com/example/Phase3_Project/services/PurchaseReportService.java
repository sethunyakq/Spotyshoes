package com.example.Phase3_Project.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Phase3_Project.model.PurchaseReport;
import com.example.Phase3_Project.respository.PurchaseReportRepository;

@Service
public class PurchaseReportService {
	@Autowired
	private PurchaseReportRepository purchaseReportRepository;
	
	public List<PurchaseReport> getPurchaseReportsByDateAndCategory(Date startDate, Date endDate, String category){
		return purchaseReportRepository.findByPurchaseDateBetweenAndCategory(startDate, endDate, category);
	}

}
