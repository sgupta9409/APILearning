package basicconcepts;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;

public class HeaderExample {
	
	@Test
	public void setSingleHeader() {
		RestAssured.given()
					.log()
					.all()
					.header("Header1", "value1")
					.get("https://restful-booker.herokuapp.com");
	}
	
	@Test
	public void setSingleHeaderWithMultipleValues() {
		RestAssured.given()
					.log()
					.all()
					// Old way to write same header with multiple values
					.header("Header3", "value1")
					.header("Header3", "value2")
					// New way to set same header with multiple values
					.header("Header2", "value1", "value2", "value3")
					.get("https://restful-booker.herokuapp.com");
	}
	
	
	@Test
	public void setHeaderValuesUsingHeaderClassObject() {
		Header header = new Header("Header1", "value1");
		RestAssured.given()
					.log()
					.all()
					.header(header)
					.get("https://restful-booker.herokuapp.com");
	}
	
	//*********************************Headers**********************************

	@Test
	public void setHeadersValues() {
		RestAssured.given()
					.log()
					.all()
					.headers("h1", "v1",
							 "h2", "v2",
					         "h3", "v3")
					.get("https://restful-booker.herokuapp.com");
	}
	@Test
	public void setHeadersValuesWithSameHeaderWithMultipleValues() {
		RestAssured.given()
					.log()
					.all()
					.headers("h1", "v1",
							 "h2", "v2",
					         "h2", "v3",
					         "h3", "v1",
					         "h3", "v2",
					         "h3", "v3")
					.get("https://restful-booker.herokuapp.com");
	}
	
	@Test
	public void setHeadersValuesUsingMap() {
		Map<String, String> headerMap = new HashMap<>();
		headerMap.put("h1", "v1");
		headerMap.put("h2", "v1");
		headerMap.put("h2", "v2");
		headerMap.put("h3", "v1");
		headerMap.put("h3", "v2");
		headerMap.put("h3", "v3");
		
		RestAssured.given()
					.log()
					.all()
					.headers(headerMap)
					.get("https://restful-booker.herokuapp.com");
	}
	
	
}
