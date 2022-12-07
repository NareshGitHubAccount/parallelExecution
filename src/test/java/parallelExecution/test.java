package parallelExecution;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class test {
	WebDriver driver;
	
	

	@Test 
	public void SoartablePerformance() throws Exception {
//		WebDriverManager.chromedriver().setup();
//		driver= new ChromeDriver();

		WebDriverManager.firefoxdriver().setup();
		driver= new FirefoxDriver();

		//maximize the window
		driver.manage().window().maximize();
		//		Navigate to application
		driver.get("https://jqueryui.com/");
		//click on droppble
		driver.findElement(By.xpath("//*[text()='Sortable']")).click();
		JavascriptExecutor js = (JavascriptExecutor)driver;
//		js.executeScript("window.scrollBy(0,1000)"," ");

		//		switch to frame
		WebElement frame = driver.findElement(By.xpath("//*[@class='demo-frame']"));

		driver.switchTo().frame(frame);

		//		perform drag and drop operation
		
		Actions act = new Actions(driver);
		WebElement one= driver.findElement(By.xpath("(//*[@class='ui-state-default ui-sortable-handle'])[1]")); 
		WebElement three= driver.findElement(By.xpath("(//*[@class='ui-state-default ui-sortable-handle'])[3]")); 
		WebElement five= driver.findElement(By.xpath("(//*[@class='ui-state-default ui-sortable-handle'])[5]"));
		WebElement seven= driver.findElement(By.xpath("(//*[@class='ui-state-default ui-sortable-handle'])[7]")); 
		Thread.sleep(1000);			
		Thread.sleep(1000);			
		act.clickAndHold(one).moveToElement(seven).release().perform();
		Thread.sleep(1000);	
		act.clickAndHold(three).moveToElement(seven).release().perform();
		Thread.sleep(1000);	
		act.clickAndHold(five).moveToElement(seven).release().perform();
		Thread.sleep(1000);	
		act.clickAndHold(seven).moveToElement(three).release().perform();
	driver.switchTo().defaultContent();
	
	String expected ="Sortable";
	WebElement sortabletext=	driver.findElement(By.xpath("(//*[text()='Sortable'])[1]"));
		String actual =sortabletext.getText();
		System.out.println(actual);

	}

}
