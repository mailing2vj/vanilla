package pages;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.WebDriver;

import utils.apputils;
import utils.commonutils;

public class HomePage {

	WebDriver driver;
	apputils app_utils = new apputils(driver);
	commonutils utils = new commonutils(driver);
	public static String base_url ="";

	By icn_HamburgerMenu = By.cssSelector("#nav-hamburger-menu");
	By lbl_HeaderTelevision = By.xpath("//div[text()='TV, Appliances, Electronics']");
	By lnk_Television = By.xpath("//a[text()='Televisions']");
	By lbl_SelectedHeader = By.cssSelector(".hmenu.hmenu-visible.hmenu-translateX  .hmenu-item.hmenu-title");
	By lbl_FilteredMenuItem = By.cssSelector("span[class*='apb-browse-refinement']");
	By lbl_FilteredBrandName = By.xpath("//div[contains(@id,'refinements')] //span[contains(text(),'Samsung')]");
	By chk_SelectedBrandName = By.xpath(
			"//div[contains(@id,'refinements')] //span[contains(text(),'Samsung')]//parent::a//i[contains(@class,'check')]");
	By lbl_BrandNameSelected = By.cssSelector("#brandsRefinements span[class*='a-text-bold']");
	By lst_SearchResults = By.cssSelector("span[class*='a-size-base-plus a-color-base a-text-normal']");
	By drp_SortByCriteria = By.cssSelector("#s-result-sort-select");
	By lbl_SelectedValueDropdwn = By.cssSelector(".a-dropdown-prompt");
	By lnk_SelectedProductData = By.cssSelector("div[cel_widget_id='MAIN-SEARCH_RESULTS-2'] a[class*='text-normal']");

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickHamburgerMenu() throws InterruptedException {
		utils.waitForElementLoad(icn_HamburgerMenu, this.driver);
		driver.findElement(icn_HamburgerMenu).click();
		Thread.sleep(1000);
	}

	public void clickTVHeader() throws InterruptedException {
		utils.waitForElementLoad(lbl_HeaderTelevision, this.driver);
		utils.scrollToElement(lbl_HeaderTelevision, this.driver);
		driver.findElement(lbl_HeaderTelevision).click();
		Thread.sleep(1000);
	}

	public boolean verifyHeaderText() {
		utils.waitForElementLoad(lnk_Television, this.driver);
		return (driver.findElement(lbl_SelectedHeader).getText().toLowerCase().contains("tv"));
	}

	public void clickTVSubMenu() {
		utils.waitForElementLoad(lnk_Television, this.driver);
		driver.findElement(lnk_Television).click();
	}

	public boolean verifySubMenuText() {
		utils.waitForElementLoad(lbl_FilteredMenuItem, this.driver);
		return (driver.findElement(lbl_FilteredMenuItem).getText().toLowerCase().contains("television"));
	}

	public void SelectBrandFilter() {
		utils.waitForElementLoad(lbl_FilteredBrandName, this.driver);
		utils.scrollToElement(lbl_FilteredBrandName, this.driver);
		driver.findElement(lbl_FilteredBrandName).click();
	}

	public boolean verifySelectedBrandName(String expected) {
		utils.waitForElementLoad(chk_SelectedBrandName, this.driver);
		return (driver.findElement(lbl_BrandNameSelected).getText().toLowerCase().contains(expected));
	}

	public boolean verifySearchResults(String expected) {
		Boolean flag = true;
		List<WebElement> search_results = driver.findElements(lst_SearchResults);
		for (WebElement result : search_results) {
			if (!result.getText().toLowerCase().contains(expected))
				flag = false;
		}
		return flag;
	}
	
	public void sortbyPrice(String value_to_select) throws InterruptedException {
		utils.waitForElementLoad(drp_SortByCriteria, this.driver);
		utils.selectfromDrpDown(drp_SortByCriteria, value_to_select, this.driver);		
		Thread.sleep(1000);
	}
	
	public boolean verifySelectedOptionInSort(String value_to_select)
	{
		return driver.findElement(lbl_SelectedValueDropdwn).getText().toLowerCase().contains(value_to_select.toLowerCase());
	}
	
	public void selectProduct(String index)
	{	
		String locator="div[cel_widget_id='MAIN-SEARCH_RESULTS-"+index+"'] a[class*='text-normal']";
		lnk_SelectedProductData = By.cssSelector(locator);
		utils.waitForElementLoad(lnk_SelectedProductData, this.driver);
		driver.findElement(lnk_SelectedProductData).click();		
	}
	
	public boolean switchToNewWindow()
	{
		base_url= utils.getcurrentURL(this.driver);
		utils.switchWindow(this.driver);
		return (! base_url.equalsIgnoreCase(utils.getcurrentURL(this.driver)));
		
	
		
	}
	

}
