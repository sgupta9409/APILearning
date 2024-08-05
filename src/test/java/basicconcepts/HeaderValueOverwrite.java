package basicconcepts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.config.HeaderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;

public class HeaderValueOverwrite {
	//Note: Default all headers are merged except the *accept* and *content-type* headers
	
	
	// By default if the same header have different values so in API all header values are used
	// To overwrite and use the only latest value we can use overwrite method
	
	@Test
	public void overwriteHeaderValue() {
		RestAssured.given()
					.log()
					.all()
					.config(RestAssuredConfig.config()
							.headerConfig(HeaderConfig.headerConfig().overwriteHeadersWithName("Header 1", "Header 2")))
					
					.header("Header 1", "Value 1")
					.header("Header 1", "Value 2")
					.header("Header 2", "Value 3")
					.header("Header 2", "Value 4")
					.header("Header 3", "Value 5")
					.header("Header 3", "Value 6")
					.get("https://restful-booker.herokuapp.com");
		/* Output Data
		 * Headers:		
		 * 		Header 1=Value 2
				Header 2=Value 4
				Header 3=Value 5
				Header 3=Value 6
		*/
		}
	
	// If user uses the 2 config to overwrite the vales, then the last config data uses in Test
	
	@Test
	public void overwriteHeaderValueWithDifferentConfig() {
		RestAssured.given()
					.log()
					.all()
					.config(RestAssuredConfig.config()
							.headerConfig(HeaderConfig.headerConfig().overwriteHeadersWithName("Header 1", "Header 2")))
					
					.config(RestAssuredConfig.config()
							.headerConfig(HeaderConfig.headerConfig().overwriteHeadersWithName("Header 1")))
					
					.header("Header 1", "Value 1")
					.header("Header 1", "Value 2")
					.header("Header 2", "Value 3")
					.header("Header 2", "Value 4")
					.header("Header 3", "Value 5")
					.header("Header 3", "Value 6")
					.get("https://restful-booker.herokuapp.com");
		/* Output Data
		 * Headers:		
		        Header 1=Value 2
				Header 2=Value 3
				Header 2=Value 4
				Header 3=Value 5
				Header 3=Value 6
		*/
		}
	
	@Test
	public void realTimeHeaderOverwrite() {
		
		RequestSpecification res1 = RestAssured.given()
						 .header("Header 1", "Value 1");
		
		RequestSpecification res2 = RestAssured.given()
				 .header("Header 2", "Value 2")
				 .header("Header 1", "Value 3");
		
		RestAssured.given()
					.log()
					.all()
					.config(RestAssuredConfig.config().headerConfig(HeaderConfig.headerConfig().overwriteHeadersWithName("Header 1")))
					.spec(res1)
					.spec(res2)
					.get("https://restful-booker.herokuapp.com/booking/1")
					.then()
					.log()
					.all();
		/*
		 *      Header 2=Value 2
				Header 1=Value 3
		*/
		}

}
