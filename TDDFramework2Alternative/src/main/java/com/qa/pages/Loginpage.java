package com.qa.pages;

import org.openqa.selenium.support.PageFactory;

import com.qa.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Loginpage  {
	BaseTest base;
	public Loginpage()
	{
		base=new BaseTest();
		PageFactory.initElements(new AppiumFieldDecorator(base.getDriver()), this);
	}

	
	@AndroidFindBy(accessibility = "test-Username")
	private MobileElement Username;
	@AndroidFindBy(accessibility = "test-Password")
	private MobileElement Password;
	@AndroidFindBy(accessibility = "test-LOGIN")
	private MobileElement Loginbtn;
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView")
	private MobileElement Errortxt;

	public Loginpage enteringusername(String username){
	base.sendkeys(Username, username);
	return this;
	}
	public Loginpage enteringpassword(String password)
	{
		base.sendkeys(Password, password);
		return this;
	}
	public String getErrortxt()
	{
		return base.getAttribute(Errortxt, "text");
	
	}
	public Productpage pressloginbtn()
	{
	base.click(Loginbtn)	;
	return new Productpage();
    }

}