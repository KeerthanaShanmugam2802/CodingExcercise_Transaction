package com.virginmoney.codingexercise.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virginmoney.codingexercise.entity.Transaction;
import com.virginmoney.codingexercise.exception.RecordNotFoundException;
import com.virginmoney.codingexercise.model.TransactionStatistics;
import com.virginmoney.codingexercise.repository.TransationRepository;


@Service
public class TransactionService {
	
	 @Autowired
	 TransationRepository transationRepo;
	 
	 public List<Transaction> getAllTransactionByCategory(String category) throws RecordNotFoundException{
		 List<Transaction> transactionList= new ArrayList<Transaction>();
		 try {
			 transactionList = transationRepo.findByCategoryOrderByTransactionDateDesc(category);
			 if(transactionList.size() == 0) {
				 throw new RecordNotFoundException("No transaction exist for the given category");
			 }
		 } catch(Exception ex) {
			 ex.printStackTrace();
		 }
		return transactionList;
	 }
	 
	 public List<TransactionStatistics> getTotalOutgoingBasedOnCategory() throws RecordNotFoundException{
		 List<TransactionStatistics> transactionStatList= new ArrayList<TransactionStatistics>();
		 try {
			 List<Object[]> transactionSumList= transationRepo.findTotalOutgoingByCategory();
			 
			 if(transactionSumList.size() > 0) {
				 for (Object[] obj : transactionSumList) {
					if (obj.length == 2) {
						TransactionStatistics stat = new TransactionStatistics();
						stat.setStatValue(String.valueOf(obj[0]));
						stat.setAmount(Double.parseDouble(String.valueOf(obj[1])));
						transactionStatList.add(stat);
					}
				 }
			 } else {
				 throw new RecordNotFoundException("No transaction exist");
			 }
		 } catch(Exception ex) {
			 ex.printStackTrace();
		 }
		return transactionStatList;
	 }
	 
	 public List<TransactionStatistics> getMonthlyAverageByCategory(String category) throws RecordNotFoundException{
		 List<TransactionStatistics> transactionStatList= new ArrayList<TransactionStatistics>();
		 try {
			 List<Object[]> transactionAvgList = transationRepo.findMonthlyAverageByCategory(category);
			 
			 if(transactionAvgList.size() > 0) {
				 for (Object[] obj : transactionAvgList) {
					if (obj.length == 2) {
						TransactionStatistics stat = new TransactionStatistics();
						stat.setStatValue(String.valueOf(obj[0]));
						stat.setAmount(Double.parseDouble(String.valueOf(obj[1])));
						transactionStatList.add(stat);
					}
				 }
			 } else {
				 throw new RecordNotFoundException("No transaction exist for the given category");
			 }
		 } catch(Exception ex) {
			 ex.printStackTrace();
		 }
		return transactionStatList;
	 }
	 
	 public List<Transaction> getHighestTransactionByCategoryAndYear(String category, String year) throws RecordNotFoundException{
		 List<Transaction> transactionList= new ArrayList<Transaction>();
		 try {
			 transactionList = transationRepo.findHighestTransactionByCategoryAndYear(category, year);
			 
			 if(transactionList.size() == 0) {
				 throw new RecordNotFoundException("No transaction exist for the given category and year");
			 }
		 } catch(Exception ex) {
			 ex.printStackTrace();
		 }
		return transactionList;
	 }
	 
	 public List<Transaction> getLowestTransactionByCategoryAndYear(String category, String year) throws RecordNotFoundException{
		 List<Transaction> transactionList= new ArrayList<Transaction>();
		 try {
			 transactionList = transationRepo.findLowestTransactionByCategoryAndYear(category, year);
			 
			 if(transactionList.size() == 0) {
				 throw new RecordNotFoundException("No transaction exist for the given category and year");
			 }
		 } catch(Exception ex) {
			 ex.printStackTrace();
		 }
		return transactionList;
	 }
	 
	 public List<String> getCategoryList() {
		 List<String> categoryList= new ArrayList<String>();
		 try {
			 categoryList = transationRepo.getCategoryList();
		 } catch(Exception ex) {
			 ex.printStackTrace();
		 }
		return categoryList;
	 }

	public List<Long> getYearList() {
		 List<Long> yearList= new ArrayList<Long>();
		 try {
			 yearList = transationRepo.getYearList();

		 } catch(Exception ex) {
			 ex.printStackTrace();
		 }
		return yearList;
	 }
}
