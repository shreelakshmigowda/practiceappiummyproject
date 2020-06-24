package com.qa;

import org.testng.annotations.Test;

import com.qa.utils.TestUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class BaseTest {
protected static AppiumDriver driver;
protected static Properties props;
InputStream inputstream;
protected static HashMap<String, String>
strings =new HashMap<String, String>();
InputStream stringis;
TestUtils utils;
//Initialize pagefactory element
public BaseTest()
{
PageFactory.initElements(new AppiumFieldDecorator(driver), this);
}

@Parameters({"platformName","platformVersion", "deviceName"})
  @BeforeTest
  public void beforeTest(String platformname, String platformversion, String devicename) {
	  
	  try{
		props =new Properties();
		String propFilename = "config.properties";
		String xmlFileName = "strings/strings.xml";
		inputstream =getClass().getClassLoader().getResourceAsStream(propFilename);
		props.load(inputstream);
		stringis= getClass().getClassLoader().getResourceAsStream(xmlFileName);
		utils = new TestUtils();
		strings= utils.parsestringxml(stringis);
		
		  
	  DesiredCapabilities cap = new DesiredCapabilities();
	  cap.setCapability("PlatformName", platformname);
	  cap.setCapability("platformVersion", platformversion);
	  cap.setCapability("deviceName",devicename);
	  cap.setCapability("automationName", props.getProperty("androidAutomationName"));
	  cap.setCapability("appActivity",props.getProperty("appActivityName"));
	  cap.setCapability("appPackage", props.getProperty("appPackageName"));
	 
   URL androidAppUrl = getClass().getClassLoader().getResource(props.getProperty("androidAppLoc"));
		
		cap.setCapability("app", androidAppUrl);
		
	  URL url= new URL(props.getProperty("appiumURL"));
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
		 WebDriverWait wait =new WebDriverWait(driver, TestUtils.WAIT);//remove hardcoding of secons, create util package
		 wait.until(ExpectedConditions.visibilityOf(e));
	  }
  public void click(MobileElement e)
  
  {
	  WaitForVisibility(e);
	  e.click();
  }
  
  public void sendkeys(MobileElement e, String txt)
  {
	  WaitForVisibility(e);
	  e.sendKeys(txt);
  }
  public String getAttribute(MobileElement e, String attribute)
  {
	  WaitForVisibility(e);
	  return e.getAttribute(attribute);
  }
  @AfterTest
  public void afterTest() {
  }

}
