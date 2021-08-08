package org.testcases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.pageObjects.ChooseFlightsPageObjects;
import org.pageObjects.FindFlightsPageObjects;

public class TestCase1 extends BaseClass{

	public static void main(String[] args) {
		BaseClass base = new BaseClass();
		base.launchBrowser();
		base.toSetUrl("https://blazedemo.com/");


		FindFlightsPageObjects findObj = new FindFlightsPageObjects();
		WebElement fromPort = findObj.getFromPort();
		base.selectDropDownByValue(fromPort, "Mexico City");
		WebElement toPort = findObj.getToPort();
		base.selectDropDownByValue(toPort, "Berlin");
		findObj.getBtnSubmit().click();

		ChooseFlightsPageObjects objFlight = new ChooseFlightsPageObjects();
		List<WebElement> price = objFlight.getPriceData();

		List<Float>  money = new ArrayList<Float>();

		for (int i = 0; i < price.size(); i++) {
			WebElement webEle = price.get(i);

			String pd = webEle.getText().replace("$", "");
			System.out.println(pd);
			money.add(Float.parseFloat(pd));

		}


		System.out.println(money);

		Float minPrice = Collections.min(money);

		System.out.println("minimum price is  "+minPrice);

		String minpr = minPrice.toString();
		
		for (int i = 0; i<= price.size(); i++) {
			WebElement webEle = price.get(i);

			String pd = webEle.getText();
			
			if (pd.contains(minpr)) {

				WebElement btnChooseFlight = driver.findElement(By.xpath
						("//td[contains(text(),'"+"\'"+minpr+"\'"+"')]/preceding::td[5]"));
				
				btnChooseFlight.click();
				break;
				
				
				
			}
			
		}




	}




}
