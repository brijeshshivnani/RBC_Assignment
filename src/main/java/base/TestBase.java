package base;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class TestBase {
    public static WebDriver driver;
	public static Properties CONFIG =null;
	public static Properties OR =null;
	public static FileInputStream fs=null;

   public  TestBase(){
	    CONFIG= new Properties();
		OR = new Properties();
		try{
			FileInputStream fs = new FileInputStream("src//main//java//config/config.properties");
			CONFIG.load(fs);
			fs = new FileInputStream("src//main//resources/OR.properties");
			OR.load(fs);
			}catch(Exception e){
				e.printStackTrace();
		}
   }
   
   public static void initialization() throws InterruptedException {
			
			System.out.println(CONFIG.getProperty("browser"));
			if(CONFIG.getProperty("browser").equals("FF")) {
				System.setProperty("webdriver.chrome.driver", CONFIG.getProperty("firefoxDriverPath"));
			    driver=new FirefoxDriver();
			}
			else if(CONFIG.getProperty("browser").equals("IE")) {
				System.setProperty("webdriver.chrome.driver", CONFIG.getProperty("ieriverPath"));
			    driver=new InternetExplorerDriver();   
			}
			else if(CONFIG.getProperty("browser").equals("CH")){
				System.setProperty("webdriver.chrome.driver", CONFIG.getProperty("chromeDriverPath"));
				
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-geolocation");
			    driver=new ChromeDriver(options);  
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(CONFIG.getProperty("baseUrl"));
			Thread.sleep(3000);
   }

	
	public static void click(String xpathKey){
		try{
	        driver.findElement(By.xpath(OR.getProperty(xpathKey))).click();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void inputText(String xpathKey, String text){
		try{
		driver.findElement(By.xpath(OR.getProperty(xpathKey))).sendKeys(text);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void keyPressEnter(String xpathKey){
		try{
		driver.findElement(By.xpath(OR.getProperty(xpathKey))).sendKeys(Keys.TAB);
		driver.findElement(By.xpath(OR.getProperty(xpathKey))).sendKeys(Keys.ENTER);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static String getText(String xpathKey){
		return driver.findElement(By.xpath(OR.getProperty(xpathKey))).getText();
	}
	
	
	public static String getAttribute(String xpathKey, String attributeName){
		return driver.findElement(By.xpath(OR.getProperty(xpathKey))).getAttribute(attributeName);
	}
	

	public static boolean isElementPresent(String xpathKey){
		try{
			driver.findElement(By.xpath(OR.getProperty(xpathKey)));
		}catch(Exception e){
			return false;
		}
		
		return true;
	}
	
	public List<WebElement> getElementsList(String xpathKey){
		return driver.findElements(By.xpath(xpathKey));}
	
	public static void loginLoblaws(String userName, String password) {
		click("btn_signIn");
		inputText("input_userName",userName);
		inputText("input_password",password);
		click("button_submit");
		}


}

