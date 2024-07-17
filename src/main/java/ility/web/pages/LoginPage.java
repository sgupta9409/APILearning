package ility.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ility.web.utilities.WaitTool;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public LoginPage setEmail(String email) {
		WaitTool.waitForPresenceOfElement(driver, By.id("username")).sendKeys(email);
		return this;
	}
	public LoginPage setPassword(String password) {
		driver.findElement(By.id("password")).sendKeys(password);
		return this;
	}
	public Homepage clickLoginButton() {
		driver.findElement(By.cssSelector("button.submit-button-new")).click();
		return new Homepage(driver);
	}

}
