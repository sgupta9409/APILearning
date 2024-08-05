package basicconcepts;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class UseOfDefaultVariables {

	// if basrUri and BasePath not defined then API is pointing to http://localhost:8080/
	// In RestAssured class we have static default vales so URI is picking from default value
	
	//defaultBaseURI
	public static void main(String[] args) {
		RestAssured
		.given()
		.log()
		.all()
		//.baseUri("https://restful-booker.herokuapp.com")
		//.basePath("/booking")
		.contentType(ContentType.JSON)
		.body("{\r\n"
			+ "    \"firstname\" : \"Shubham\",\r\n"
			+ "    \"lastname\" : \"Gupta\",\r\n"
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
		.all();
	}
	
	//*****************************************************************************
	// We can set base URI and base path means default values at class level and suite level
	// No need to pass in test because it will rewrite the default value local host to the set value
	
	@BeforeTest
	public void setUp() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		RestAssured.basePath = "/booking";
		System.out.println("In Setup");
	}
	
	@Test
	public void createBooking() {
		RestAssured
		.given()
		.log()
		.all()
		.contentType(ContentType.JSON)
		.body("{\r\n"
			+ "    \"firstname\" : \"Maninder\",\r\n"
			+ "    \"lastname\" : \"Singh\",\r\n"
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
		.statusCode(200);
	}
	
	@Test
	public void createBooking1() {
		RestAssured
		.given()
		.log()
		.all()
		.contentType(ContentType.JSON)
		.body("{\r\n"
			+ "    \"firstname\" : \"Harjinder\",\r\n"
			+ "    \"lastname\" : \"Singh\",\r\n"
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
		.statusCode(200);
	}
	
	
}
