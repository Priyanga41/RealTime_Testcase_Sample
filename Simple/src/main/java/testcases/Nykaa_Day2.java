package testcases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Nykaa_Day2 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		
	//1. Launch the URL
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		
	//2. Mouseover on brands and popular
		WebElement Brands = driver.findElementByXPath("//li[@class='menu-dropdown-icon']/a");
		WebElement Popular = driver.findElementByXPath("//div[@class='BrandsCategoryHeading']/a");
		
		Actions builder = new Actions(driver);
		builder.moveToElement(Brands).click().perform();
		Thread.sleep(2000);
		builder.moveToElement(Popular).click().perform();
		
	//3. CLick on L'oreal paris
		driver.findElementByXPath("//img[@src='https://adn-static2.nykaa.com/media/wysiwyg/2019/lorealparis.png']").click();
		
	//4. Go to the newly opened window and check the title contains L'Oreal Paris
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> listofwindows = new ArrayList<>();
		listofwindows.addAll(windowHandles);
		
		driver.switchTo().window(listofwindows.get(1));
		String title = driver.getTitle();
		System.out.println(title);
		
		if(title.contains("L'Oreal Paris")) {
			System.out.println("Title of the page contains L'Oreal Paris");
		}
		
	//5. Click sort By and select customer top rated
		driver.findElementByXPath("//i[@class='fa fa-angle-down']").click();
		driver.findElementByXPath("(//div[contains(@class,'control control--radio')]//div)[4]").click();
		
	//6. Click Category and click Shampoo
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//div[text()='Category']")));
		
		builder.moveToElement(driver.findElementByXPath("//div[text()='Category']")).click().perform();
		Thread.sleep(2000);
		builder.moveToElement(driver.findElementByXPath("(//label[@for='chk_Shampoo_undefined']//div)[2]")).click().perform();
		System.out.println("Shampoo clicked");
		
	//7. check whether the Filter is applied with Shampoo
		WebElement Shampoo = driver.findElementByXPath("(//label[@for='chk_Shampoo_undefined']//div)[2]");
		if(Shampoo.isDisplayed())
		{
			System.out.println("Shampoo is selected");
		}
	
	//8. Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElementByXPath("//div[@class='m-content__product-list__title']//span[contains(text(),'Protect')]").click();
		System.out.println("L'Oreal Color Protect Shampoo Clicked");
			
	//9. Go to next window and select size as 175ml
		Set<String> windowHandles1 = driver.getWindowHandles();
		List<String> listofwindows1 = new ArrayList<>();
		listofwindows1.addAll(windowHandles1);
		driver.switchTo().window(listofwindows1.get(2));
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='175ml']")));
		driver.findElementByXPath("//span[text()='175ml']").click();
		
	//10. Print the MRP of the product
		String price = driver.findElementByXPath("(//span[@class='post-card__content-price-offer'])[1]").getText();
		String text = price.replaceAll("\\D", "");
		int Price = Integer.parseInt(text);
		System.out.println("MRP: " +Price);
				
	//11. Click on ADD to BAG
		driver.findElementByXPath("//div[@class='pull-left']//button").click();
		driver.findElementByXPath("//div[@class='AddBagIcon']").click();
		System.out.println("Added to cart");
		
	//12. Go to Shopping Bag 
		Thread.sleep(3000);
		driver.findElementByXPath("//div[@class='second-col']//button").click();
		
	//13. Print the Grand Total amount
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//div[@class='value'])[2]")));
		String Total = driver.findElementByXPath("(//div[@class='value'])[2]").getText();
		String text1 = Total.replaceAll("\\D", "");
		int total = Integer.parseInt(text1);
		System.out.println("Grand Total :"+total);
		
	//14. Click Proceed
		driver.findElementByXPath("(//button[contains(@class,'btn full')])[2]").click();
		
	//15. Continue as guest
		driver.findElementByXPath("(//button[@type='button'])[2]").click();
		Thread.sleep(3000);
		
	//16. Print the warning message
		String Warning = driver.findElementByClassName("message").getText();
		System.out.println(Warning);
		
	//17. Close all windows
		driver.quit();
		
	}

}
