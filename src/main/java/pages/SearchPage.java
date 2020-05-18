package pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import base.TestBase;

public class SearchPage extends TestBase {

	public SearchPage() {
		super();
		
	}
	
	public void searchProduct() {
		inputText("input_productSearch",CONFIG.getProperty("product"));
		keyPressEnter("input_productSearch");
	}

	public boolean verifySearchIsSorted() throws InterruptedException {
		isElementPresent("btn_sortHighToLow");
		click("btn_sortHighToLow");
		boolean sorted = verifySearchSortedHighToLow();
		return sorted;
	}
	
	public boolean verifyItemInCart() throws Exception{
		String productName = getAttribute("span_firstProduct","title");
		click("btn_addToCart");
		click("span_pcExpressPickup");
		click("span_PCExpressLocation");
		selectLocation();
		Thread.sleep(2000);
		Assert.assertTrue(getText("span_cartCount").contains("1"));
		click("span_cartCount");
		click("link_viewCart");
		System.out.println(">>>>>"+getText("div_cartEntry"));
		return getText("div_cartEntry").contains(productName);
		}
	
	public boolean verifySearchSortedHighToLow() throws InterruptedException{
		List <WebElement> priceList = getElementsList(OR.getProperty("span_priceList"));
		
		for(int i=0; i<priceList.size()-1; i++) {
			float currPrice = Float.parseFloat(priceList.get(i).getText().replace("$",""));
			System.out.println("current price --" + currPrice);
			float currPriceNext = Float.parseFloat(priceList.get(i+1).getText().replace("$",""));
			System.out.println("current price next --" + currPriceNext);
			if(currPrice<currPriceNext) {
				return false;
			}
			}
			return true;
			
		}
	
	public void changeLocation(){
		click("div_pickUpDetails");
		click("link_changeLocation");
		inputText("input_enterPostcode",CONFIG.getProperty("Address"));
		keyPressEnter("input_enterPostcode");
		click("span_selectLocation");
		click("link_pickContinue");
	}
	
	public void selectLocation() throws Exception{
		click("link_storeLocator");
		inputText("input_enterPostcode",CONFIG.getProperty("Address"));
		Thread.sleep(2000);
		keyPressEnter("input_enterPostcode");
		click("span_selectLocation");
		click("link_pickContinue");
	}
	
	public void selectFilter() {
		click("button_promotionFilterCollapsed");
		click("input_priceDeduction");
	}
	
	public void verifyLocation(){
		click("div_pickUpDetails");
		click("link_locationDetails");
		System.out.println("Selected Location Address -"+ getText("div_addressDetails"));
		Assert.assertTrue(getText("div_addressDetails").contains(CONFIG.getProperty("Address")));
	}
	
	public void verifyFlyer(){
		click("link_storeflyer");
		System.out.println("flyer address -"+ getText("div_flyerLocationDetails"));
		Assert.assertTrue(getText("div_flyerLocationDetails").contains(CONFIG.getProperty("Address1")));
	}
	
	public boolean verifyPriceReductionMatch() throws InterruptedException{
		List <WebElement> newPriceList = getElementsList(OR.getProperty("span_priceList"));
		List <WebElement> oldPriceList = getElementsList(OR.getProperty("span_oldPriceList"));
		List <WebElement> savingAmountList = getElementsList(OR.getProperty("span_savingList"));
		for(int i=0; i<newPriceList.size()-1; i++) {
			double currPrice = Double.parseDouble(newPriceList.get(i).getText().replace("$",""));
			double oldPrice = Double.parseDouble(oldPriceList.get(i).getText().replace("$", ""));
			double savings = Double.parseDouble(savingAmountList.get(i).getText().replace("SAVE $",""));
			
			System.out.println("currPrice --" + currPrice);
			System.out.println("oldPrice --" + oldPrice);
			System.out.println("savings --" + savings);
			System.out.println("sum --" + Double.sum(currPrice, savings));
			if(Double.sum(currPrice, savings)!=oldPrice) {
				return false;
			}
			}
			return true;
		}
		
}
