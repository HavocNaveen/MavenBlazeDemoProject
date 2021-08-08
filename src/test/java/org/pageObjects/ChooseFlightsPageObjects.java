package org.pageObjects;

import java.util.List;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChooseFlightsPageObjects extends BaseClass{

	public ChooseFlightsPageObjects(){
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//td[6]")
	private List<WebElement> priceData;


	public List<WebElement> getPriceData() {
		return priceData;
	}








}
