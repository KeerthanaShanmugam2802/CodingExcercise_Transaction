package com.virginmoney.codingexercise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.virginmoney.codingexercise.entity.Transaction;
 
@Repository
public interface TransationRepository extends JpaRepository<Transaction, Long> {
 
	public List<Transaction> findByCategoryOrderByTransactionDateDesc(String category);
	
	
	@Query(value = "SELECT CATEGORY, SUM(AMOUNT) FROM TB_TRANSACTION "
			+ "GROUP BY CATEGORY", nativeQuery = true)
	public List<Object[]> findTotalOutgoingByCategory();
	
	
	@Query(value = "SELECT  MONTHNAME(transaction_date), Avg(AMOUNT) FROM TB_TRANSACTION "
			+ " WHERE CATEGORY = :CATEGORY GROUP BY  MONTHNAME(transaction_date)", nativeQuery = true)
	public List<Object[]> findMonthlyAverageByCategory(@Param("CATEGORY") String category);
	
	
	@Query(value = "SELECT  ID, TRANSACTION_DATE, VENDOR, TRANSACTION_TYPE, AMOUNT, CATEGORY "
			+ "FROM TB_TRANSACTION  WHERE category = :CATEGORY AND year(TRANSACTION_DATE) = :YEAR "
			+ "AND AMOUNT = (SELECT MAX(AMOUNT) FROM TB_TRANSACTION "
			+ "WHERE category = :CATEGORY AND year(TRANSACTION_DATE) = :YEAR)", nativeQuery = true)
	public List<Transaction> findHighestTransactionByCategoryAndYear(@Param("CATEGORY") String category, @Param("YEAR") String year);
	
	
	@Query(value = "SELECT  ID, TRANSACTION_DATE, VENDOR, TRANSACTION_TYPE, AMOUNT, CATEGORY "
			+ "FROM TB_TRANSACTION  WHERE category = :CATEGORY AND year(TRANSACTION_DATE) = :YEAR "
			+ "AND AMOUNT = (SELECT MIN(AMOUNT) FROM TB_TRANSACTION "
			+ "WHERE category = :CATEGORY AND year(TRANSACTION_DATE) = :YEAR)", nativeQuery = true)
	public List<Transaction> findLowestTransactionByCategoryAndYear(@Param("CATEGORY") String category, @Param("YEAR") String year);

	@Query(value = "SELECT DISTINCT category FROM TB_TRANSACTION", nativeQuery = true)
	public List<String> getCategoryList();
	
	@Query(value = "SELECT DISTINCT year(TRANSACTION_DATE) FROM TB_TRANSACTION", nativeQuery = true)
	public List<Long> getYearList();
}
