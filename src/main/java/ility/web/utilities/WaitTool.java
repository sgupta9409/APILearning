package ility.web.utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitTool {

	public static WebElement waitForPresenceOfElement(WebDriver driver, By by) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		return wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}
}
