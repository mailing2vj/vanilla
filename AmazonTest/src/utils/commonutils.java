package utils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class commonutils {

	static ExtentTest test;
	static ExtentReports report;

	WebDriver driver;

	public commonutils(WebDriver driver) {
		this.driver = driver;
	}

	public void launchBrowser(String url) {

		driver.manage().window().maximize();
		driver.get(url);
		String file = System.getProperty("user.dir") + "\\Reports\\TestResults.html";
		File report_file = new File(file);
		report_file.delete();
	}

	public void waitForElementLoad(By element,WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(element));
	}
	
	public void selectfromDrpDown(By element,String value,WebDriver driver){
		 Select select = new Select(driver.findElement(element));
		 select.selectByVisibleText(value);
	}
	
	public void scrollToElement(By element,WebDriver driver){
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(element));
	    try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public String getcurrentURL(WebDriver driver)
	{
		return driver.getCurrentUrl();
	}
	
	public void switchWindow(WebDriver driver)
	{
	 String mainWindowHandle = driver.getWindowHandle();
	    Set<String> allWindowHandles =  driver.getWindowHandles();
	    Iterator<String> iterator = allWindowHandles.iterator();
	    

	    // Here we will check if child window has other child windows and will fetch the heading of the child window
	    while (iterator.hasNext()) {
	        String ChildWindow = iterator.next();
	        driver.switchTo().window(ChildWindow);
	    }
	}

	public void shutdown() {

		report.endTest(test);
		report.flush();
		driver.quit();
	}
}
