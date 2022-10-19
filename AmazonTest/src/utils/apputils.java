package utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;


public class apputils {
	
	
	
	 public WebDriver driver;
	 
	 public apputils(WebDriver driver) {
			this.driver=driver;
		}
	
	 public boolean verifyHomePageLoaded()
	 {
		 String parent_url=driver.getCurrentUrl();
		   return parent_url.contains("amazon.in");		    	
	 }
	 
	 
	
}
