package testcases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;


public class Myntra_Day1 {
public static void main(String[] args) throws InterruptedException {
		
		//Disable location notification
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		//1. Open browser and launch the page 
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver(options);
		
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		
		//2&3 Find and mouse over the elements
		WebElement women = driver.findElementByXPath("(//a[@href='/shop/women'])[1]");
		WebElement coats = driver.findElementByXPath("(//a[@href='/women-jackets-coats'])");
		
		Actions builder = new Actions(driver);
		builder.moveToElement(women).pause(2000).perform();		
		builder.moveToElement(coats).pause(1000).click().perform();
		
		//4. Get the total count of coats & Jackets
		String Count = driver.findElementByClassName("title-count").getText();
		String text = Count.replaceAll("\\D", "");
		int count = Integer.parseInt(text);
		System.out.println("Count of coats & Jackets:" +count);
		
		//Get the count of coats & jackets sepertly
		String Jaccount = driver.findElementByXPath("(//label[\"common-customCheckbox vertical-filters-label\"])[1]/span").getText();
		String text1 = Jaccount.replaceAll("\\D", "");
		int count1 = Integer.parseInt(text1);
		System.out.println("Count of Jackets: " +count1);
		
		String coatcount = driver.findElementByXPath("(//label[\"common-customCheckbox vertical-filters-label\"])[2]/span").getText();
		String text2 = coatcount.replaceAll("\\D", "");
		int count2 = Integer.parseInt(text2);
		System.out.println("Count of coats: " +count2);
		
		//5. Sum the coats & jacket and verify that it matches the total
		int Total = count1+count2;
		if(count == Total) {
			System.out.println("Count Matches: " +Total);
		}
	
		//6 & 7.Check coats and expand brand
		driver.findElementByXPath("(//div[@class='common-checkboxIndicator'])[2]").click();
		driver.findElementByXPath("//div[@class='brand-more']").click();
		
		//8. Type mango and check box
		driver.findElementByClassName("FilterDirectory-searchInput").sendKeys("Mango");
		driver.findElementByXPath("//label[@class=' common-customCheckbox']//div[1]").click();
		
		//9. close the pop-up and wait
		driver.findElementByXPath("//span[contains(@class,'myntraweb-sprite FilterDirectory-close')]").click();
		Thread.sleep(3000);
		
		//10. List the product brand
		List<WebElement> brand = driver.findElementsByXPath("//h3[@class='product-brand']");
		boolean condition = false;
		for (WebElement eachBrand : brand) {
			String brandname = eachBrand.getText();
				
		//verify by compare all brands are MANGO
		if(brandname.equalsIgnoreCase("MANGO") ) {
			condition = true; } //inner if-loop
		} //for-loop end
		
		if(condition = true) { 
			System.out.println("All coats are Mango Brand");
		}
		
		//11. Sort by better discount
		WebElement dropdown = driver.findElementByXPath("//div[@class='sort-sortBy']");
		builder.moveToElement(dropdown).perform();
		driver.findElementByXPath("//ul[@class='sort-list']/li[3]/label").click();
		
		//12. Find the price of first displayed item
		List<WebElement> price = driver.findElementsByXPath("//span[@class='product-discountedPrice']");
		String firstcoat = price.get(0).getText();
		System.out.println("Price of first displayed coat is: " + firstcoat);
		
		//13. Mouse over on size of the first item
		WebElement size = driver.findElementByXPath("//span[@class='product-discountedPrice']");
		builder.moveToElement(size).perform();
		
		//14. Click on WishList Now
		driver.findElementByXPath("//span[(@class='product-actionsButton product-wishlist product-prelaunchBtn')]").click();
			
		//15. Close Browser
		driver.close();
		
}


}
