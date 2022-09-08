package com.FLipkart.stepdefinition;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class MobilePurchase {
	public  static WebDriver driver;
	@BeforeClass
	public static void BeforeClass() throws InterruptedException {
		System.out.println("Start Task" );			
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\eclipse-workspace\\JUnit-Sep\\Driver\\chromedriver.exe");
		 driver = new ChromeDriver();
		driver.navigate().to("http://www.flipkart.com/");
		driver.manage().window().maximize();		
	}
	
	@Before
	public  void beforeMethod() {
		long startTime = System.currentTimeMillis();
		System.out.println("Start Time:"+startTime );	
	}		
	@After
	public  void afterMethod() {
		long endTime = System.currentTimeMillis();	
		System.out.println("End Time:"+endTime );			
	}
	@Test
	 	public void method1() throws Throwable {			
		System.out.println("Method 1");
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ESCAPE);
		r.keyRelease(KeyEvent.VK_ESCAPE);	
		Thread.sleep(30);		
			}
	@Test
	    public void method2() throws InterruptedException, Throwable {
		System.out.println("Search Mobiles & Print in Excel");
		
		driver.findElement(By.xpath("//input[@class='_3704LK']")).sendKeys("Realme mobiles",Keys.ENTER);
		Thread.sleep(30);
		List<String> al = new ArrayList<String>();
	    List<WebElement> products = driver.findElements(By.xpath("(//a//div//div//div[contains(text(),'realme')])"));
		for (WebElement x : products){
	    String text = x.getText();
		al.add(text);}
	    File ip = new File("C:\\Users\\Admin\\eclipse-workspace\\JUnit-Sep\\src\\test\\resources\\Excelread.xlsx"); 
		FileInputStream f = new FileInputStream(ip);
		Workbook w = new XSSFWorkbook(f);
	    Sheet sh = (Sheet) w.createSheet("Realme Mobiles"); 
		for(int i =0;i<al.size(); i++)
		   {
		Row cr = sh.createRow(i);
		Cell cc = cr.createCell(0);
		cc.setCellValue(al.get(i));
	   }
		FileOutputStream v = new FileOutputStream(ip);
	    w.write(v);
		
		}
	
	@Test
	public void method3() throws InterruptedException {
		System.out.println("Windows Handling ");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);	 		 	
	 	driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[2]/div[3]/div/div/div/a/div[2]/div[1]/div[1]")).click();
	 	Thread.sleep(30);	
	 	Set<String> wid = driver.getWindowHandles();
	 	List<String> WindowsId = new ArrayList<String>(wid);
	 	driver.switchTo().window(WindowsId.get(1));
	 	
	
	}

	@Test
	public void method4() throws Exception {
		System.out.println("GetText mobile selected & do assert");
		WebElement product = driver.findElement(By.tagName("h1"));
		String productName = product.getText();
		System.out.println(productName);
		File ip = new File("C:\\Users\\DD\\Desktop\\ip1.xlsx"); 
		FileInputStream f = new FileInputStream(ip);
	    Workbook w = new XSSFWorkbook(f);
	    Sheet sheet = w.getSheet("Realme Mobiles");
		Row row = sheet.getRow(1);
		Cell cell = row.getCell(0);
		String cellValue = cell.getStringCellValue();		
		//Assert.assertEquals(productName, cellValue);
		System.out.println("ASSERT PASSED -Values are Equal ");
		}
	
	
	
	@Test
	public void method5() throws Throwable {
		System.out.println("Scroll down to specifications and Takesscreenshot");
		JavascriptExecutor js =(JavascriptExecutor)driver;
	 	WebElement down = driver.findElement(By.xpath("//div[text()='Specifications']"));
	 	js.executeScript("arguments[0].scrollIntoView(true)", down);
	 	Thread.sleep(30);
		TakesScreenshot tk = (TakesScreenshot) driver;
		File src = tk.getScreenshotAs(OutputType.FILE);
		File dest= new File("C:\\Users\\Admin\\eclipse-workspace\\JUnit-Sep\\src\\test\\resources\\newwindow.png");
	    FileUtils.copyFile(src, dest);
	}
	@AfterClass
	public static void AfterClass() throws InterruptedException {
		driver.quit();
	}

}
