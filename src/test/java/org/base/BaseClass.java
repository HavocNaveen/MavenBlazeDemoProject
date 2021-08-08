package org.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;




public class BaseClass {
	public static WebDriver driver;
	public static Actions  act ;
	public static Robot r;
	public static Alert alert;
	public static Select select;
	public WebDriver launchBrowser() {
		System.setProperty("webdriver.chrome.driver", 
				"C:\\Users\\BABU\\eclipse-workspace\\TestingHome\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;


	}public void toSetUrl(String url) {
		driver.get(url);

	}


	public void toEnterText(WebElement element, CharSequence text) {

		element.sendKeys(text);

	}public void toClearAndEnterText(WebElement element, CharSequence text) {
		element.clear();

		element.sendKeys(text);}



	public void toClickAButton(WebElement element) {
		element.click();
	}


	public void todDoubleClick(WebElement ele) {
		act= new Actions(driver);
		act.doubleClick(ele).build().perform();


	}

	public void toUseKeys(int key) throws AWTException {
		r= new Robot();
		r.keyPress(key);
		r.keyRelease(key);


	}
	public void toAcceptAlert() {
		alert = driver.switchTo().alert();
		alert.accept();

	}

	public void toDismissAlert () {
		alert = driver.switchTo().alert();


	}


	public void alertWithTextbox() {
		alert = driver.switchTo().alert();


	}

	public void toTakeScreenShot(String destFile) throws IOException {

		TakesScreenshot sc=(TakesScreenshot) driver;
		File srcImg = sc.getScreenshotAs(OutputType.FILE);
		File destImg = new File(destFile+".png");
		FileHandler.copy(srcImg, destImg);
         
	}

	public void toUseJS(WebElement ele,String data) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value','"+data+"')", ele);


	}
	public void selectDropDownByIndex(WebElement element,int index) {
		select= new Select(element);
		select.selectByIndex(index);


	}
	public void selectDropDownByValue(WebElement element,String  value) {
		select= new Select(element);
		select.selectByValue(value);


	}
	public void selectDropDownByVisibleText(WebElement element,String  visibleText) {
		select= new Select(element);
		select.selectByValue(visibleText);


	}

	public void toHandleWindows() {
		String parentWindow = driver.getWindowHandle();
		Set<String> childWindows = driver.getWindowHandles();
		for (String string : childWindows) {
			driver.switchTo().window(string);

		}


	}

	public String getDataFromExcel( int rowNo) throws Exception {
		File file = new File("C:\\Users\\S.BABU\\eclipse-workspace\\TestingHome\\TestData\\Book1.xlsx");
		FileInputStream stream = new FileInputStream(file);
		Workbook book = new XSSFWorkbook(stream);
		Sheet sheet = book.getSheet("Sheet1");
		Row row = sheet.getRow(rowNo);
		Cell cell =  row.getCell(0);



		String data="";
		int cellType = cell.getCellType();

		if (cellType==1) {
			data=cell.getStringCellValue();


		}else if (DateUtil.isCellDateFormatted(cell)) {
			Date d = cell.getDateCellValue();
			SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy"); 
			data=dateFormat.format(d);		
		}else {
			double cellValue = cell.getNumericCellValue();
			long val = (long) cellValue;
			data = String.valueOf(val);

		}


		return data;

	}


	public void putDataIntoExcel(String orderId,int rowNo) throws IOException {
		File file = new File("C:\\Users\\S.BABU\\eclipse-workspace\\TestingHome\\TestData\\Book1.xlsx");
		FileInputStream stream = new FileInputStream(file);
		Workbook book = new XSSFWorkbook(stream);
		Sheet sheet = book.createSheet("OrderId");
		Row row = sheet.createRow(rowNo);
		Cell cell = row.createCell(0);
		cell.setCellValue(orderId);
		FileOutputStream opt = new FileOutputStream(file);
		book.write(opt);

	}
	public void closeBrowser() {
		driver.quit();
	}


}
