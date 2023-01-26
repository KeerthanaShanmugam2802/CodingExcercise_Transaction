package com.virginmoney.codingexercise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.virginmoney.codingexercise.entity.Transaction;
import com.virginmoney.codingexercise.exception.RecordNotFoundException;
import com.virginmoney.codingexercise.model.DropdownValue;
import com.virginmoney.codingexercise.model.TransactionStatistics;
import com.virginmoney.codingexercise.service.TransactionService;

@Controller
public class TransactionController {

	@Autowired
	TransactionService transactionService;

	@GetMapping("/codingexercise")
	public String showHomePage() {
		return "homePage";
	}

	@GetMapping("/transactions/getAllTransactionByCategory/{category}")
	public ResponseEntity<List<Transaction>> getAllTransactionByCategory(@PathVariable("category") String category)
			throws RecordNotFoundException {
		List<Transaction> entityList = transactionService.getAllTransactionByCategory(category);
		return new ResponseEntity<List<Transaction>>(entityList, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/transactions/getTotalOutgoingBasedOnCategory")
	public ResponseEntity<List<TransactionStatistics>> getTotalOutgoingBasedOnCategory()
			throws RecordNotFoundException {
		List<TransactionStatistics> entityList = transactionService.getTotalOutgoingBasedOnCategory();
		return new ResponseEntity<List<TransactionStatistics>>(entityList, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/transactions/getMonthlyAverageByCategory/{category}")
	public ResponseEntity<List<TransactionStatistics>> getMonthlyAverageByCategory(
			@PathVariable("category") String category) throws RecordNotFoundException {
		List<TransactionStatistics> entityList = transactionService.getMonthlyAverageByCategory(category);
		return new ResponseEntity<List<TransactionStatistics>>(entityList, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/transactions/getHighestTransactionByCategoryAndYear/{category}/{year}")
	public ResponseEntity<List<Transaction>> getHighestTransactionByCategoryAndYear(
			@PathVariable("category") String category, @PathVariable("year") String year)
			throws RecordNotFoundException {
		List<Transaction> entityList = transactionService.getHighestTransactionByCategoryAndYear(category, year);
		return new ResponseEntity<List<Transaction>>(entityList, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/transactions/getLowestTransactionByCategoryAndYear/{category}/{year}")
	public ResponseEntity<List<Transaction>> getLowestTransactionByCategoryAndYear(
			@PathVariable("category") String category, @PathVariable("year") String year)
			throws RecordNotFoundException {
		List<Transaction> entityList = transactionService.getLowestTransactionByCategoryAndYear(category, year);
		return new ResponseEntity<List<Transaction>>(entityList, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/transactionsDropDownList")
	public ResponseEntity<DropdownValue> getCategoryList() {
		DropdownValue dropdownList = new DropdownValue();
		try {
			 dropdownList.setCategoryList(transactionService.getCategoryList());
			 dropdownList.setYearList(transactionService.getYearList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<DropdownValue>(dropdownList, new HttpHeaders(), HttpStatus.OK);
	}
}
