package ility.web.testcases;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseClass {

	protected static WebDriver driver = null;
	public static String browser = null;
	public static String URL = null;
	public static String useremail = null;
	public static String password = null;

	@BeforeTest
	public void initialize(ITestContext context) {
		browser = context.getCurrentXmlTest().getParameter("browser");
		useremail = context.getCurrentXmlTest().getParameter("useremail");
		password = context.getCurrentXmlTest().getParameter("password");
	}

	@Parameters({ "browser", "URL" })
	@BeforeSuite
	public WebDriver setUp(String browser, String URL) throws Exception {
		driver = launchBrowser(browser);
		launchURL(URL);
		return driver;

	}
	
	@AfterSuite
	public void tearDown() {
		driver.quit();
	}

	public WebDriver launchBrowser(String browser) throws Exception {

		try {

			String download =System.getProperty("user.dir")+File.separator+"download";
			File file = new File(download);

			if(!file.exists())
				file.mkdir();

			if(browser.equalsIgnoreCase("chrome")) {

				//set download directory
				HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
				chromePrefs.put("profile.default_content_settings.popups", 0);
				chromePrefs.put("download.default_directory", download);

				//set chrome options
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", chromePrefs);
				options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
				options.setAcceptInsecureCerts(true);
				options.addArguments("force-device-scale-factor=0.75");
				options.addArguments("high-dpi-support=0.75");
				options.addArguments("enable-automation");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-extensions");
				options.addArguments("--dns-prefetch-disable");
				options.addArguments("--disable-gpu");
				options.addArguments("--disable-notifications");
				options.addArguments("--start-maximized");
				options.addArguments("test-type");
				System.setProperty("java.net.preferIPv4Stack", "true");	

				//Launch browser

				//WebDriverManager.chromedriver().clearResolutionCache().setup();
				driver = new ChromeDriver(options);

			} else if(browser.equalsIgnoreCase("firefox")) {

				//Launch browser
				//WebDriverManager.firefoxdriver().clearResolutionCache().setup();
				driver = new FirefoxDriver();

			} else if(browser.equalsIgnoreCase("iexplorer")) {

				//Launch browser
				//WebDriverManager.iedriver().clearResolutionCache().setup();
				driver = new InternetExplorerDriver();

			} else {

				throw new InvalidArgumentException("Invalid browser name");

			}

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("return document.readyState").equals("complete");

		} catch (Exception e) {

			System.out.println("Not able to launch browser: " + e.getMessage());
			throw e;

		}
		return driver;
	}

	public void launchURL(String URL) throws Exception {
		try {

			driver.get(URL);
			System.out.println(URL + " URL launched successfully.");

		} catch (Exception e) {
			System.out.print("Not able to launch URL: " + e.getMessage());
			throw e;
		}
	}

	public static WebDriver getDriver() {

		return driver;

	}
}
