package basicconcepts;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class UseOfDefaultVariables {

	// if basrUri and BasePath not defined then API is pointing to http://localhost:8080/
	// In RestAssured class we have static default vales so URI is picking from default value
	
	@Test
	public void defaultBaseURI() {
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
		.all()
		.statusCode(200)
		.contentType(ContentType.JSON)
		.time(Matchers.lessThan(5000L));
	}
}
