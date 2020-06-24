package com.qa.tests;

import org.testng.annotations.Test;

import com.qa.BaseTest;
import com.qa.pages.Loginpage;
import com.qa.pages.Productpage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class LoginTest extends BaseTest {
	Loginpage loginpage;
	Productpage productpage;
	InputStream datais;
	JSONObject LoginUsers;
	 
	 @BeforeClass
	  public void beforeClass() throws IOException {
		 try {
			 String dataFileName = "data/LoginUsers.json";
			 datais= getClass().getClassLoader().getResourceAsStream(dataFileName);
			 JSONTokener tokener = new JSONTokener(datais);
			 LoginUsers = new JSONObject(tokener);
			 
		 }
		 
		 
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 
		 finally
		 {
			 if(datais != null)
				 datais.close();
		 }}
	  
	 @BeforeMethod
	  public void beforeMethod() {
		  loginpage =new Loginpage();
	  }
  @Test
  public void invalidusername() {
	  loginpage.enteringusername(LoginUsers.getJSONObject("invalidUser").getString("username"));
	  loginpage.enteringpassword(LoginUsers.getJSONObject("invalidUser").getString("password"));
	  loginpage.pressloginbtn();
	  
	  
	  String actualerrortxt=loginpage.getErrortxt();
	  String expectederrortxt=strings.get("err_invalid_username_or_Password");
	  System.out.println("actual error text- " + actualerrortxt+ "\n" +"expected error text- " +expectederrortxt);
	  Assert.assertEquals(actualerrortxt, expectederrortxt);
  }
  @Test
  public void invalidpassword() {
	  loginpage.enteringusername(LoginUsers.getJSONObject("invalidpwd").getString("username"));
	  loginpage.enteringpassword(LoginUsers.getJSONObject("invalidpwd").getString("password"));
	  loginpage.pressloginbtn();
	  
	  String actualerrortxt=loginpage.getErrortxt();
	  String expectederrortxt=strings.get("err_invalid_username_or_Password");;
	  System.out.println("actual error text- " + actualerrortxt+ "\n" +"expected error text- " +expectederrortxt);
	  Assert.assertEquals(actualerrortxt, expectederrortxt);
  }
  @Test
  public void successfullogin() {
	  loginpage.enteringusername(LoginUsers.getJSONObject("successfullogin").getString("username"));
	  loginpage.enteringpassword(LoginUsers.getJSONObject("successfullogin").getString("password"));
	 productpage= loginpage.pressloginbtn();
	  
	  String actualproducttitle=productpage.getTitle();
	  String expectedproducttitle=strings.get("product_title");;
	  System.out.println("actual product title- " + actualproducttitle+ "\n" +"expected product title- " +expectedproducttitle);
	  Assert.assertEquals(actualproducttitle, expectedproducttitle);
  }
  
  
  @AfterMethod
  public void afterMethod() {
  }

 

  @AfterClass
  public void afterClass() {
  }

}
