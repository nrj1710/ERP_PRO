package dFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.customerPage;
import config.AppUtil1;
import utilities.ExcelFileUtil;

public class AppTest extends AppUtil1 {
	String inputpath = "./FileInput/customerID.xlsx";
	String outputpath = "./Fileoutput/customerResults.xlsx";
	ExtentReports report;
	ExtentTest logger;
	
	@Test
	public void startTest() throws Throwable
	{
		report = new ExtentReports("./target/Reports/Customer.html");
		customerPage cus = PageFactory.initElements(driver,customerPage.class);
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		int rc = xl.rowCount("customer");
		Reporter.log("No. of Rows::"+rc,true);
		
		for(int i=1;i<=rc;i++)
		{
			logger = report.startTest("customer");
			logger.assignAuthor("Deepak");
			logger.assignCategory("Customer ID Test");
			String customerName = xl.getCellData("customer", i, 0);
			String Address = xl.getCellData("customer", i, 1);
			String city = xl.getCellData("customer", i, 2);
			String country = xl.getCellData("customer", i, 3);
			String contactPerson = xl.getCellData("customer", i, 4);
			String phoneNum = xl.getCellData("customer", i, 5);
			String email = xl.getCellData("customer", i, 6);
			String mobNum = xl.getCellData("customer", i, 7);
			String Notes = xl.getCellData("customer", i, 8);
			logger.log(LogStatus.INFO, customerName+"   "+Address+"   "+city+"    "+
			country+"   "+contactPerson+"    "+phoneNum+"   "+email+"   "+mobNum+"    "+Notes);
			boolean res = cus.add_customer(customerName, Address, city, country, contactPerson, phoneNum, email, mobNum, Notes);
			if(res)
			{
				xl.setCellData("customer", i, 9, "Pass", outputpath);
				logger.log(LogStatus.PASS, "Customer Added Successfully");
			}else
			{
				xl.setCellData("customer", i, 9, "Fail", outputpath);
				logger.log(LogStatus.PASS, "Customer Not Added Successfully");
				
			}
			report.endTest(logger);
			report.flush();
		}
				
	}

}












