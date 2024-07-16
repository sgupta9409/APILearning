package basicconcepts;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RequestAndResponseSpecification2 {
	RequestSpecification requestSpecification;
	
	// Using private methods
	// If Base URI set then no need to pass in request spec. it will pick from before class
	// Use private method instead of putting request specification in before class because we can not parameterize it
	
	
	@BeforeClass
	public void setRequestSpec() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";				
	}
	
	
	private RequestSpecification requestSpec(String basePath) {
		return RestAssured
				.given()
				.log()
				.all()
				.basePath(basePath)
				.contentType(ContentType.JSON);
	}
	
	private ResponseSpecification responseSpec() {
		return RestAssured
				.expect()
				.statusCode(200)
				.contentType(ContentType.JSON)
				.time(Matchers.lessThan(5000L));
	}
	
	
	
	@Test
	public void testCreateBooking() {
		requestSpec("booking")
		
		.body("{\r\n"
			+ "    \"firstname\" : \"Jim\",\r\n"
			+ "    \"lastname\" : \"Brown\",\r\n"
			+ "    \"totalprice\" : 111,\r\n"
			+ "    \"depositpaid\" : true,\r\n"
			+ "    \"bookingdates\" : {\r\n"
			+ "        \"checkin\" : \"2018-01-01\",\r\n"
			+ "        \"checkout\" : \"2019-01-01\"\r\n"
			+ "    },\r\n"
			+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
			+ "}")
		.post()
		.then()
		.log()
		.all()
		.spec(responseSpec());
	}
	
	@Test
	public void testUpdateBooking() {
		requestSpec("booking/1")
			
		.header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
		.body("{\r\n"
				+ "    \"firstname\" : \"Test\",\r\n"
				+ "    \"lastname\" : \"Brown\",\r\n"
				+ "    \"totalprice\" : 111,\r\n"
				+ "    \"depositpaid\" : true,\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2018-01-01\",\r\n"
				+ "        \"checkout\" : \"2019-01-01\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
				+ "}")
		.put()
		.then()
		.log()
		.all()
		.spec(responseSpec());
	}

}
