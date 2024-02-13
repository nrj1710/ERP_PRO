package commonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminLoginPage {
	//define Repository
	@FindBy(xpath = "//button[@id='btnreset']")
	WebElement ObjResetbtn;
	@FindBy(name = "username")
	WebElement Objuser;
	@FindBy(id = "password")
	WebElement Objpass;
	@FindBy(xpath = "//button[@id='btnsubmit']")
	WebElement ObjLoginbtn;
	
	public void adminLoginPage(String user,String pass)
	{
		ObjResetbtn.click();
		Objuser.sendKeys(user);
		Objpass.sendKeys(pass);
		ObjLoginbtn.click();
	}

}
