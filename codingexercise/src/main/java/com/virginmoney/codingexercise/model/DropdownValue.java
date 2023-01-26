package com.virginmoney.codingexercise.model;

import java.util.List;

public class DropdownValue {
	private List<String> categoryList;
	private List<Long> yearList;
	
	public List<String> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<String> categoryList) {
		this.categoryList = categoryList;
	}
	public List<Long> getYearList() {
		return yearList;
	}
	public void setYearList(List<Long> yearList) {
		this.yearList = yearList;
	}
}
