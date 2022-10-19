package test;

import java.util.List;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import org.junit.Assert;
import utils.*;

import pages.*;

public class BaseTest {

	static ExtentTest test;
	static ExtentReports report;

	public static void main(String[] args) throws InterruptedException, IOException {

		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		HomePage homepage = new HomePage(driver);
		ProductDetailsPage productpage = new ProductDetailsPage(driver);
		apputils app_utils = new apputils(driver);
		commonutils utils = new commonutils(driver);

		setup_prerequisites();

		try {
			utils.launchBrowser("https://www.amazon.in/");
			if (app_utils.verifyHomePageLoaded())
				test.log(LogStatus.PASS, "Navigated to the specified URL - https://www.amazon.in/");
			else
				test.log(LogStatus.FAIL, "Navigated to the specified URL - https://www.amazon.in/");
			test.log(LogStatus.PASS, test.addScreenCapture(capture(driver)) + "");

			homepage.clickHamburgerMenu();
			test.log(LogStatus.PASS,"Clicked the hamburger menu");
			test.log(LogStatus.PASS,test.addScreenCapture(capture(driver))+ "");
			
			homepage.clickTVHeader();
			test.log(LogStatus.PASS,"Clicked the TV header in the menu");
			test.log(LogStatus.PASS,test.addScreenCapture(capture(driver))+ "");
			
			if(homepage.verifyHeaderText())
				test.log(LogStatus.PASS, "TV header is selected in the menu");
			else
				test.log(LogStatus.FAIL, "TV header is not selected in the menu");
			test.log(LogStatus.PASS, test.addScreenCapture(capture(driver)) + "");
			
			homepage.clickTVSubMenu();
			test.log(LogStatus.PASS,"Clicked the TV the sub menu");
			test.log(LogStatus.PASS,test.addScreenCapture(capture(driver))+ "");
			
			if(homepage.verifySubMenuText())
				test.log(LogStatus.PASS, "TV is applied in the filter");
			else
				test.log(LogStatus.FAIL, "TV is not applied in the filter");
			test.log(LogStatus.PASS, test.addScreenCapture(capture(driver)) + "");
			
			homepage.SelectBrandFilter();
			test.log(LogStatus.PASS,"Filtered the requested brand name");
			test.log(LogStatus.PASS,test.addScreenCapture(capture(driver))+ "");
			
			if(homepage.verifySelectedBrandName("samsung"))
				test.log(LogStatus.PASS, "The selected brand is filtered as expected and the filtered values have 'Samsung' in them");
			else
				test.log(LogStatus.FAIL, "The selected brand is not filtered as expected and the filtered values donot have 'Samsung' in them");
			
			if(homepage.verifySearchResults("samsung"))
				test.log(LogStatus.PASS, "Search results returned are filtered properly by the given criteria");
			else
				test.log(LogStatus.FAIL, "Search results returned are not filtered by the given criteria");
			test.log(LogStatus.PASS, test.addScreenCapture(capture(driver)) + "");
			
			homepage.sortbyPrice("Price: High to Low");
			if(homepage.verifySelectedOptionInSort("Price: High to Low"))
				test.log(LogStatus.PASS,"Sorted the results with - Price: High to Low");
			else
				test.log(LogStatus.FAIL,"Sorting the results with - Price: High to Low failed");
			test.log(LogStatus.PASS,test.addScreenCapture(capture(driver))+ "");

			homepage.selectProduct("2");
			test.log(LogStatus.PASS,"Selected the product at index : 2 ,   from the results displayed");
			
			if(homepage.switchToNewWindow())
				test.log(LogStatus.PASS,"Switched to the newly opened tab");
			else
				test.log(LogStatus.FAIL,"Failed to switch to the newly opened tab");
			test.log(LogStatus.PASS,test.addScreenCapture(capture(driver))+ "");
			
			productpage.checkPageLoaded();
			test.log(LogStatus.PASS,"Page loaded properly");
			
			if(productpage.ChkAbtThisItemDisplayed())
				test.log(LogStatus.PASS,"About This Item label is present in the page");
			else
				test.log(LogStatus.FAIL,"About This Item label is not present in the page");
				
			
			productpage.ClickShowMoreBtn();
			test.log(LogStatus.PASS,"Clicked the Show More button to view all the features");
			test.log(LogStatus.PASS,test.addScreenCapture(capture(driver))+ "");
			
			if(productpage.CheckFeaturesList())
				test.log(LogStatus.PASS,"Features are listed properly");
			else
				test.log(LogStatus.FAIL,"Features are not listed properly");
			
			test.log(LogStatus.PASS, "The Features listed in the UI are : ");
			for (WebElement values:productpage.features)
				test.log(LogStatus.PASS,values.getText().toString());
			test.log(LogStatus.PASS,test.addScreenCapture(capture(driver))+ "");
			
			
		}
		
		catch(Exception e)
		{
			System.out.println("Exception Occured ! ");
			System.out.println(e.toString());
		}

		finally {
			System.out.println(" D O N E ! ! ! ");
			report.endTest(test);
			report.flush();
			driver.quit();
		}

	}

	public static String capture(WebDriver driver) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File Dest = new File("src/../BStackImages/" + System.currentTimeMillis() + ".png");
		String errflpath = Dest.getAbsolutePath();
		FileUtils.copyFile(scrFile, Dest);
		return errflpath;
	}

	public static void setup_prerequisites() {
		String file = System.getProperty("user.dir") + "\\Reports\\TestResults.html";
		File report_file = new File(file);
		report = new ExtentReports(file);

		test = report.startTest("WebTest_Amazon");
	}

}
