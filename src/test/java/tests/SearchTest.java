package tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import base.TestBase;
import pages.SearchPage;

public class SearchTest extends TestBase {
	SearchPage searchPage;
	
	public SearchTest() {
		super();
	}

	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		searchPage = new SearchPage();
	}

//	Test 1.	Search for apples and sort the search results from highest price to lowest price.
	@Test(priority=1,enabled=true)
	   public void productSearch() throws InterruptedException{
		searchPage.searchProduct();
		Assert.assertTrue(searchPage.verifySearchIsSorted());;
	   }
	
//	Test 2.	Search for apples and use the Price Reduction option under Filter By > Promotion to sort the search by sale badges. 
	@Test(priority=2,enabled=true)
	public void verifyCart() throws Exception {
		searchPage.searchProduct();
		Assert.assertTrue(searchPage.verifyItemInCart());
	}
	
//	Test 3.	As a new user to the site, add an item to your cart
	@Test(priority=3,enabled=true)
	public void verifyFilterPromotion() throws Exception {
		searchPage.searchProduct();
		searchPage.selectFilter();
		Assert.assertTrue(searchPage.verifyPriceReductionMatch());
	}
	
//	Test 4.	From the home page, click on “Change Location” or “Choose a location” if selecting location 
//	first time, search for “QUEEN STREET WEST” and select it for pickup, Go to location details
//	by clicking the link present on same page.
	@Test(priority=4,enabled=true)
	public void verifyLocation() throws Exception {
//		login is not working on LoBlaws site through automation
//		loginLoblaws(CONFIG.getProperty("username"),CONFIG.getProperty("password"));
		searchPage.selectLocation();
		searchPage.verifyLocation();
		searchPage.verifyFlyer();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}

