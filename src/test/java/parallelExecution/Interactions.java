package parallelExecution;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Interactions {
 	WebDriver driver;
	 ExtentReports extent;
	ExtentSparkReporter spark;
	ExtentTest test;
	private static Logger logger = Logger.getLogger(Interactions.class);



	@BeforeTest
	@Parameters("browser")
	public void launchBrowser(String browser) {

		PropertyConfigurator.configure("Log4j.properties");



		//launch particular browser
		switch (browser.toLowerCase()) {

		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
			driver = null;
			break;
		}


		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());//time stamp
		String repName=browser+"Test-Report-"+timeStamp+".html";

		spark=new ExtentSparkReporter(System.getProperty("user.dir")+ "/test-output/"+repName);
		extent = new ExtentReports();
		extent.attachReporter(spark);

		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("Environemnt","QA");
		extent.setSystemInfo("Tester","Naresh");
		extent.setSystemInfo("browser", browser);

		spark.config().setDocumentTitle("JQUERY Testing"); // Tile of report
		spark.config().setReportName("JQUERY Test Automation Report"); // name of the report
		spark.config().setTheme(Theme.DARK);

		spark.viewConfigurer()
		.viewOrder()
		.as(new ViewName[] { 
				ViewName.DASHBOARD, 
				ViewName.TEST, 
				ViewName.AUTHOR, 
				ViewName.DEVICE, 
				ViewName.EXCEPTION, 
				ViewName.LOG, 
		})
		.apply();

		logger.info("launch browser_____"+browser);
		//maximize the window
		logger.info("maximize the browser");
		driver.manage().window().maximize();
		//navigate to application
		logger.info("navigate to application");
		driver.get("https://jqueryui.com/");




	}
	@Test (priority=1)
	public void draggingPerformance() {
		// create the report for test case
		logger.info("create the report for test case");
		test = extent.createTest("draggingPerformance");
		//click on Draggable
		logger.info("click on Draggable");
		driver.findElement(By.xpath("//*[text()='Draggable']")).click();
		//switch to frame
		logger.info("switch to frame");
		WebElement frame = driver.findElement(By.xpath("//*[@src=\"/resources/demos/draggable/default.html\"]"));
		driver.switchTo().frame(frame);
		//create the variable for draggable element
		logger.info("create the variable for draggable element");
		WebElement drag = driver.findElement(By.id("draggable"));
		Actions act = new Actions(driver);
		//perform the drag operation
		logger.info("perform the drag operation");
		act.dragAndDropBy(drag, 50, 50).perform();

		test.log(Status.PASS,MarkupHelper.createLabel("draggingPerformance", ExtentColor.GREEN));

		//take screenshot
		String path= CaptureScreenshot("draggingPerformance.jpg");
		test.info(MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		//switch to default content
		logger.info("switch to default content");
		driver.switchTo().defaultContent();
	}

	@Test (priority=2)
	public void droppblePerformance() {
		// create the report for test case
		logger.info("create the report for test case");
		test = extent.createTest("droppblePerformance");
		//click on Droppable
		logger.info("click on Droppable");
		driver.findElement(By.xpath("//*[text()='Droppable']")).click();
		//switch to frame 
		logger.info("switch to frame ");
		WebElement frame = driver.findElement(By.xpath("//*[@class='demo-frame']"));
		driver.switchTo().frame(frame);
		// create the variable for dragable element
		logger.info("create the variable for dragable element");
		WebElement drag = driver.findElement(By.id("draggable"));
		// create the variable for droppable element
		logger.info("create the variable for droppable element");
		WebElement drap = driver.findElement(By.id("droppable"));
		Actions act = new Actions(driver);
		//perform drag and drop operation
		logger.info("perform drag and drop operation");
		act.dragAndDrop(drag,drap).perform();
		test.log(Status.PASS,MarkupHelper.createLabel("droppblePerformance", ExtentColor.GREEN));

		//take screenshot
		String path= CaptureScreenshot("droppblePerformance.jpg");
		test.info(MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		//switch to default content
		logger.info("switch to default content");
		driver.switchTo().defaultContent();
	}

	@Test(priority=3) 
	public void ResizablePerformance() {
		// create the report for test case
		logger.info("create the report for test case");
		test = extent.createTest("ResizablePerformance");
		//click on Resizable
		logger.info("click on Resizable");
		driver.findElement(By.xpath("//*[text()='Resizable']")).click();
		//switch to frame
		logger.info("switch to frame");
		WebElement frame = driver.findElement(By.xpath("//*[@class='demo-frame']"));
		driver.switchTo().frame(frame);
		//create variable for resize
		logger.info("create variable for resize");
		WebElement resize = driver.findElement(By.xpath("//*[@class='ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se']"));
		//perform the resize operation
		logger.info("perform the resize operation");
		Actions act = new Actions(driver);
		act.dragAndDropBy(resize, 90, 20).release().perform();
		test.log(Status.PASS,MarkupHelper.createLabel("ResizablePerformance", ExtentColor.GREEN));

		//take screenshot
		String path= CaptureScreenshot("ResizablePerformance.jpg");
		test.info(MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		//switch to default content
		logger.info("switch to default content");
		driver.switchTo().defaultContent();
	}

	@Test (priority=4)
	public void SelectablePerformance() throws Exception {
		// create the report for test case
		logger.info("create the report for test case");
		test = extent.createTest("SelectablePerformance");
		//click on Selectable
		logger.info("click on Selectable");
		driver.findElement(By.xpath("//*[text()='Selectable']")).click();
		//scroll the web page based on given parameters
		logger.info("scroll the web page based on given parameters");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,200)"," ");
		//switch to frame 
		logger.info("switch to frame ");
		WebElement frame = driver.findElement(By.xpath("//*[@class='demo-frame']"));
		driver.switchTo().frame(frame);


		//take the all select elements in one list
		logger.info("take the all select elements in one list");
		List<WebElement> selects = driver.findElements(By.xpath("//*[@class='ui-widget-content ui-selectee']"));

		Actions act = new Actions(driver);
		//create variable for all list of select elements
		logger.info("create variable for all list of select elements");
		WebElement one= selects.get(0); 
		WebElement three= selects.get(2); 
		WebElement five= selects.get(4); 
		WebElement seven= selects.get(6); 

		//perform the select operation
		logger.info("perform the select operation");
		act.keyDown(Keys.CONTROL).click(one).click(three).click(five).click(seven).keyUp(Keys.CONTROL).build().perform();
		test.log(Status.PASS,MarkupHelper.createLabel("SelectablePerformance", ExtentColor.GREEN));

		//take screenshot
		String path= CaptureScreenshot("SelectablePerformance.jpg");
		test.info(MediaEntityBuilder.createScreenCaptureFromPath(path).build());
	
		//switch to default content
		logger.info("switch to default content");
		driver.switchTo().defaultContent();	Thread.sleep(1000);
	}

	@Test (priority=5)
	public void SoartablePerformance() throws Exception {
		// create the report for test case
		logger.info("create the report for test case");
		test = extent.createTest("SortablePerformance");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		//click on sortable
		logger.info("click on sortable");
driver.findElement(By.xpath("//*[text()='Sortable']")).click();

		//scroll the web page based on given parameters
		logger.info("scroll the web page based on given parameters");

		js.executeScript("window.scrollBy(0,200)"," ");
		//switch to frame 
		logger.info("switch to frame ");
		WebElement frame = driver.findElement(By.xpath("//*[@class='demo-frame']"));

		driver.switchTo().frame(frame);


		//create variable for all list of sort elements
		logger.info("create variable for all list of sort elements");
		Actions act = new Actions(driver);
		WebElement one= driver.findElement(By.xpath("(//*[@class='ui-state-default ui-sortable-handle'])[1]")); 
		WebElement three= driver.findElement(By.xpath("(//*[@class='ui-state-default ui-sortable-handle'])[3]")); 
		WebElement five= driver.findElement(By.xpath("(//*[@class='ui-state-default ui-sortable-handle'])[5]"));
		WebElement seven= driver.findElement(By.xpath("(//*[@class='ui-state-default ui-sortable-handle'])[7]")); 
		Thread.sleep(1000);			 

		//perform the sort operation
		logger.info("perform the sort operation");
		Thread.sleep(1000);			
		act.clickAndHold(one).moveToElement(seven).release().perform();
		Thread.sleep(1000);	
		act.clickAndHold(three).moveToElement(one).release().perform();
		Thread.sleep(1000);	
		act.clickAndHold(five).moveToElement(three).release().perform();
		Thread.sleep(1000);	
		act.clickAndHold(seven).moveToElement(five).release().perform();
		
		//Status adding to report
		logger.info("Status adding to report");
			test.log(Status.PASS,MarkupHelper.createLabel("SoartablePerformance", ExtentColor.GREEN));
			//take screenshot
			logger.info("take screenshot");
			String path= CaptureScreenshot("SoartablePerformance.jpg");
			test.info(MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			//switch to default content
			logger.info("switch to default content");
			driver.switchTo().defaultContent();
			Thread.sleep(1000);
	}
	@AfterTest
	public void tearDown(){


		//close the browser
		logger.info("close the browser");
       driver.quit();
		// flush the report
		logger.info("flush the report");
		extent.flush();
	}

	public  String CaptureScreenshot(String fileName) {


		TakesScreenshot takeScreenshot = (TakesScreenshot)driver;
		File srcFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./Screenshots/"+ fileName);
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Screenshot take sucessfully");
		return destFile.getAbsolutePath();
	}

}