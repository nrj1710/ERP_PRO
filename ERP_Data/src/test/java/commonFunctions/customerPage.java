package commonFunctions;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class customerPage {
	//define repository
	@FindBy(xpath = "(//a[contains(text(),'Customers')])[2]")
	WebElement ObjcustomerLink;
	@FindBy(xpath = "(//span[contains(@data-caption,'Add')])[1]")
    WebElement ObjaddIcon;
	@FindBy(xpath = "//input[@id='x_Customer_Number']")
	WebElement ObjcustomerNum;
	@FindBy(xpath = "//input[@id='x_Customer_Name']")
	WebElement ObjcustomerName;
	@FindBy(xpath = "//textarea[@id='x_Address']")
	WebElement Objaddress;	
	@FindBy(xpath = "//input[@id='x_City']")
	WebElement Objcity;
	@FindBy(xpath = "//input[@id='x_Country']")
	WebElement Objcountry;
	@FindBy(xpath = "//input[@id='x_Contact_Person']")
	WebElement ObjcontPerson;
	@FindBy(id = "x_Phone_Number")
	WebElement ObjphoneNum;
	@FindBy(id = "x__Email")
	WebElement Objemail;
	@FindBy(id = "x_Mobile_Number")
    WebElement ObjmobNum;
	@FindBy(name = "x_Notes")
	WebElement ObjNotes;
	@FindBy(id = "btnAction")
	WebElement ObjClickaddbtn;
	//@FindBy(xpath = "//button[@id='btnAction']")
	//WebElement ObjclickOkbtn;
	@FindBy(xpath = "(//button[contains(text(),'OK')])[6]")
	WebElement ObjOkAlertbtn;
	@FindBy(xpath = "(//span[contains(@data-caption,'Search')])[1]")
	WebElement ObjSearchpanel;
	@FindBy(xpath="//input[@id='psearch']")
	WebElement ObjSearchBox;
	@FindBy(xpath="//button[@id='btnsubmit']")
	WebElement ObjClickSearchbtn;
	@FindBy(xpath = "//table[@class='table ewTable']/tbody/tr[1]/td[5]/div/span/span")
	WebElement webdata;
	
	public boolean add_customer(String customerName,String Address,String city,String country,String contactPerson,
			String phoneNum,String email,String mobNum,String Notes) throws Throwable
	{
		ObjcustomerLink.click();
		ObjaddIcon.click();
		String Exp_Num=ObjcustomerNum.getAttribute("value");
		ObjcustomerName.sendKeys(customerName);
		Objaddress.sendKeys(Address);
		Objcity.sendKeys(city);
		ObjcontPerson.sendKeys(contactPerson);
		Objcountry.sendKeys(country);
		ObjcontPerson.sendKeys(contactPerson);
		ObjphoneNum.sendKeys(phoneNum);
		Objemail.sendKeys(email);
		ObjmobNum.sendKeys(mobNum);
		ObjNotes.sendKeys(Notes);
		ObjClickaddbtn.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		ObjOkAlertbtn.click();
		Thread.sleep(2000);
		//if search text box is not display than click on search panel
		if(!ObjSearchBox.isDisplayed())
			ObjSearchpanel.click();
		ObjSearchBox.clear();
		ObjSearchBox.sendKeys(Exp_Num);
		ObjClickSearchbtn.click();
		Thread.sleep(3000);
		String Act_Num=webdata.getText();
		if(Exp_Num.equals(Act_Num))
		{
			Reporter.log("Customer Number is Matching"+Exp_Num+       Act_Num,true);
			return true;
		}
		else
		{
			Reporter.log("Customer Number is Not Matching"+Exp_Num+    Act_Num,true);
			return false;
		}
	}
	
	
	
	
	
	
}  
