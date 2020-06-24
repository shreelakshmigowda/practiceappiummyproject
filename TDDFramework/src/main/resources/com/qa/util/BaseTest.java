package com.qa.util;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import org.testng.annotations.BeforeTest;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class BaseTest {
protected static AppiumDriver driver;
protected static Properties props;
InputStream inputstream;


  @BeforeTest
  public void beforeTest() {
	  
	  try{
		props =new Properties();
		String propFilename = "config.properties";
		inputstream =getClass().getClassLoader().getResourceAsStream(propFilename);
		props.load(inputstream);
		  
	  DesiredCapabilities cap = new DesiredCapabilities();
	  cap.setCapability("PlatformName", "Android");
	  cap.setCapability("platformVersion", "9.0");
	  cap.setCapability("deviceName","Emulator123");
	  cap.setCapability("automationName", props.getProperty("androidAutomationName"));
	  cap.setCapability("appActivity",props.getProperty("appActivityName"));
	  cap.setCapability("appPackage", props.getProperty("appPackageName"));
	  
	  URL url= new URL("http://127.0.0.1:4723/wb/hub");
	  driver = new AndroidDriver(url, cap);
	  String sessionId =driver.getSessionId().toString();
	  

	  
	  
	  }
	  catch (Exception e)
	  {
		  e.printStackTrace();
	  }
  }
	  public void WaitForVisibility (MobileElement e)
	  {
		 WebDriverWait wait =new WebDriverWait(driver, 10);//remove hardcoding of secons, create util package
		 wait.until(ExpectedConditions.visibilityOf(e));
	  }
  

  @AfterTest
  public void afterTest() {
  }

}
