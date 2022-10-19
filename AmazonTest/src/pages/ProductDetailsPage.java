package pages;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.apputils;
import utils.commonutils;

public class ProductDetailsPage {

	WebDriver driver;
	apputils app_utils = new apputils(driver);
	commonutils utils = new commonutils(driver);
	public String feature_list = null; 
	public List<WebElement> features = null;

	By lbl_ProductTitle = By.cssSelector("#productTitle");
	By ul_ProductFeatures = By.cssSelector("#feature-bullets");
	By lbl_ViewMore = By.cssSelector("#feature-bullets .a-expander-prompt");
	By lbl_AbtThisItem = By.cssSelector(".a-size-base-plus.a-text-bold");
	By lbl_ProductFeatureListItems = By.cssSelector(" #feature-bullets ul .a-list-item");

	public ProductDetailsPage(WebDriver driver) {
		this.driver = driver;
	}

	public void checkPageLoaded() throws InterruptedException {
		utils.waitForElementLoad(lbl_ProductTitle, this.driver);
		Thread.sleep(1000);
	}
	
	public boolean ChkAbtThisItemDisplayed() throws InterruptedException {
		utils.waitForElementLoad(lbl_AbtThisItem, this.driver);
		utils.scrollToElement(lbl_AbtThisItem, this.driver);
		return driver.findElement(lbl_AbtThisItem).isDisplayed();
	}
	
	
	public void ClickShowMoreBtn() throws InterruptedException {
		utils.waitForElementLoad(ul_ProductFeatures, this.driver);
		utils.scrollToElement(ul_ProductFeatures, this.driver);
		driver.findElement(lbl_ViewMore).click();
		Thread.sleep(1000);
	}
	
	public boolean CheckFeaturesList()
	{		
		features = driver.findElements(lbl_ProductFeatureListItems);
		for(WebElement value : features)
			feature_list+=value.getText()+" \n \n";
	 return (features.size() > 0);	
	}
	


}
