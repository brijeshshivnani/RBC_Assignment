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

	@Test(priority=1,enabled=true)
	   public void productSearch() throws InterruptedException{
		searchPage.searchProduct();
		Assert.assertTrue(searchPage.verifySearchIsSorted());;
	   }
	
	@Test(priority=2,enabled=true)
	public void verifyCart() throws Exception {
		searchPage.searchProduct();
		Assert.assertTrue(searchPage.verifyItemInCart());
	}
	
	@Test(priority=3,enabled=true)
	public void verifyFilterPromotion() throws Exception {
		searchPage.searchProduct();
		searchPage.selectFilter();
		Assert.assertTrue(searchPage.verifyPriceReductionMatch());
	}
	
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

