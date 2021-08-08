package org.pageObjects;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FindFlightsPageObjects extends BaseClass{
	public FindFlightsPageObjects() {
		PageFactory.initElements(driver, this);


	}
	@FindBy(name="fromPort")
	private WebElement fromPort;
	@FindBy(name="toPort")
	private WebElement toPort;
	@FindBy(xpath="//*[@type='submit']")
	private WebElement btnSubmit;
	public WebElement getFromPort() {
		return fromPort;
	}
	public WebElement getToPort() {
		return toPort;
	}
	public WebElement getBtnSubmit() {
		return btnSubmit;
	}




}
