package com.qa.tests;

import org.testng.annotations.Test;

import com.qa.BaseTest;
import com.qa.pages.Loginpage;
import com.qa.pages.Productpage;

import io.appium.java_client.AppiumDriver;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell; 
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class LoginTest  {
	Loginpage loginpage;
	Productpage productpage;
	BaseTest base;
	InputStream datais;
	XSSFWorkbook workbook;
    XSSFSheet sheet;
    XSSFCell cell;
    FileOutputStream foutput;
 
	
	@Parameters({"platformName","platformVersion", "deviceName"})
	 @BeforeClass
	 public void beforeClass(String platformname, String platformversion, String devicename) throws IOException {
		 base=new BaseTest();
		 base.initializeDriver(platformname, platformversion, devicename);
		 
		 String dataFilename= "Data/TestData.xlsx";
		 datais = getClass().getClassLoader().getResourceAsStream(dataFilename);
		workbook =new XSSFWorkbook(datais);//Load the workbook
		 sheet = workbook.getSheetAt(0); //Load the sheet
		 //for(int  i=1; i&lt;=sheet.getLastRowNum(); i++) //&lt;  stands for left tag
		 
		 
	  }
	 @BeforeMethod
	  public void beforeMethod() {
		  loginpage =new Loginpage();
	  }

  @Test
  public void invalidusernamepwd() {
	  
	  for(int i=1;i<=2; i++)
	  {
	
	  cell = sheet.getRow(i).getCell(1);
      cell.setCellType(Cell.CELL_TYPE_STRING);
      String username= cell.getStringCellValue();
      
      cell = sheet.getRow(i).getCell(2);
      cell.setCellType(Cell.CELL_TYPE_STRING);
      String password =cell.getStringCellValue();

	   loginpage.enteringusername(username); 
	   loginpage.enteringpassword(password);
	   loginpage.pressloginbtn();
	  
	  
	  String actualerrortxt=loginpage.getErrortxt();
	  String expectederrortxt="Username and password do not match any user in this service.";
	  System.out.println("actual error text- " + actualerrortxt+ "\n" +"expected error text- " +expectederrortxt);
	  Assert.assertEquals(actualerrortxt, expectederrortxt);
	  
	 
	
           

	  }  
  }
 
@Test
  public void successfullogin() throws InterruptedException {
	  cell = sheet.getRow(3).getCell(1);
      cell.setCellType(Cell.CELL_TYPE_STRING);
      String username= cell.getStringCellValue();
      
      cell = sheet.getRow(3).getCell(2);
      cell.setCellType(Cell.CELL_TYPE_STRING);
      String password =cell.getStringCellValue();

	   loginpage.enteringusername(username); 
	   loginpage.enteringpassword(password);
	   productpage= loginpage.pressloginbtn();
	  Thread.sleep(5000);
	  String actualproducttitle=productpage.getTitle();
	  String expectedproducttitle="PRODUCTS";
	  System.out.println("actual product title- " + actualproducttitle+ "\n" +"expected product title- " +expectedproducttitle);
	  Assert.assertEquals(actualproducttitle, expectedproducttitle);
  }
  
  
  
 

  @AfterClass
  public void afterClass() {
	  base.quitDriver();
  }

}
