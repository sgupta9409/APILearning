package basicconcepts;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RequestAndResponseSpecification {
	RequestSpecification requestSpecification;
	ResponseSpecification responseSpecification;
	
	//Using Before Class 
	// if we create request spec in before all then we have new method called spec in rest assured
	// In update booking please check Remove .spec method and use overloaded given method
	
	
	@BeforeClass
	public void setRequestSpec() {
		requestSpecification = RestAssured
									.given()
									.log()
									.all()
									.baseUri("https://restful-booker.herokuapp.com")
									.contentType(ContentType.JSON);
		
		responseSpecification = RestAssured
									.expect()
									.statusCode(200)
									.contentType(ContentType.JSON)
									.time(Matchers.lessThan(5000L));						
	}
	
	@Test
	public void testCreateBooking() {
		RestAssured
		.given()
		.spec(requestSpecification)
		
		
		.basePath("/booking")
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
		.spec(responseSpecification);
	}
	
	@Test
	public void testUpdateBooking() {
		
		RestAssured
		.given(requestSpecification)
		
		
		.basePath("/booking/1")
		.header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
		.body("{\r\n"
				+ "    \"firstname\" : \"James\",\r\n"
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
		.assertThat()
		.spec(responseSpecification);
	}

}
