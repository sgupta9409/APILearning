package ility.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ility.web.utilities.WaitTool;

public class Homepage {

	WebDriver driver;

	public Homepage(WebDriver driver) {
		this.driver = driver;
	}
		
	public Homepage waitForPresenceOfHeader() {
		WaitTool.waitForPresenceOfElement(driver, By.cssSelector("app-tenant-home h1"));
		return this;
	}
}
