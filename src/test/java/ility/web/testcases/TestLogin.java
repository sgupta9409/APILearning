package ility.web.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import ility.web.pages.LoginPage;

public class TestLogin extends BaseClass{

	@Test
	public void testLogin() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.setEmail(useremail)
				 .setPassword(password)
				 .clickLoginButton()
				 .waitForPresenceOfHeader();
		Assert.assertEquals(driver.getTitle(), "Feed");
	}
}
