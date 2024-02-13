package driverFactory;


	import java.io.File;

	import org.apache.commons.io.FileUtils;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.testng.Reporter;
	import org.testng.annotations.Test;

	import com.relevantcodes.extentreports.ExtentReports;
	import com.relevantcodes.extentreports.ExtentTest;
	import com.relevantcodes.extentreports.LogStatus;

	import commonFunctions.FunctionLibrary;
	import config.AppUtil;
	import utilities.ExcelFileUtil;

	public class AppTest extends AppUtil {
		String inputpath = "./FileInput/LoginData.xlsx";
		String outputpath = "./FileOutput/DataDrivenResults.xlsx";
		ExtentReports report;
		ExtentTest logger;
		@Test
		public void StartTest() throws Throwable
		{
			//define path of html report
			report = new ExtentReports("./target/Reports/LoginReports.html");
			//create object ExcelFileUtil
			ExcelFileUtil xl = new ExcelFileUtil(inputpath);
			//count no. of row in login Sheet
			int rc = xl.rowCount("Login");
			Reporter.log("No. of Rows::"+rc,true);
			//iterate all row in login sheet
			for(int i=1;i<rc;i++)
			{
				logger=report.startTest("Validate Login");
				logger.assignAuthor("Deepak");
				logger.assignCategory("Functional Testing");
				String username = xl.getCellData("Login", i, 0);
				String password = xl.getCellData("Login", i, 1);
				logger.log(LogStatus.INFO,username+"----"+ password);
				//call login method for function library class
				boolean res = FunctionLibrary.adminLogin(username, password);
				if(res)
				{
					//if res is true write login success in Result cell
					xl.setCellData("Login", i, 2, "Login Success", outputpath);
					//if res is true write login pass in status cell
					xl.setCellData("Login", i, 3, "Pass", outputpath);
					logger.log(LogStatus.PASS, "username and password are valid");
					
				}else
				{
					//take screen shot for fail
					File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(screen,new File("./Screenshot/Iteration/"+i+"LoginPage.png"));
					//if res is false write login fail in Result cell
					xl.setCellData("Login", i, 2, "Login Fail", outputpath);
					//if res is false write login fail in status cell
					xl.setCellData("Login", i, 3, "Fail", outputpath);
					logger.log(LogStatus.FAIL, "username and password are not valid");
				}
				report.endTest(logger);
				report.flush();
				
			}
		}

}
