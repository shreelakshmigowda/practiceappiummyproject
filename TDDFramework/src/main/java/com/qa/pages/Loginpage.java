package com.qa.pages;

import com.qa.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Loginpage extends BaseTest {

	
	@AndroidFindBy(accessibility = "test-Username")
	private MobileElement Username;
	@AndroidFindBy(accessibility = "test-Password")
	private MobileElement Password;
	@AndroidFindBy(accessibility = "test-LOGIN")
	private MobileElement Loginbtn;
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView")
	private MobileElement Errortxt;

	public Loginpage enteringusername(String username){
	sendkeys(Username, username);
	return this;
	}
	public Loginpage enteringpassword(String password)
	{
		sendkeys(Password, password);
		return this;
	}
	public String getErrortxt()
	{
		return getAttribute(Errortxt, "text");
	
	}
	public Productpage pressloginbtn()
	{
	click(Loginbtn)	;
	return new Productpage();
    }

}