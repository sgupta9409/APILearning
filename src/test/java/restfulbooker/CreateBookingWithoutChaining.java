package restfulbooker;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class CreateBookingWithoutChaining {

	@Test
	public void createBooking() {
		//Build Request
		RequestSpecification requestSpecification = RestAssured.given().log().all();
		
		requestSpecification.baseUri("https://restful-booker.herokuapp.com");
		requestSpecification.basePath("/booking");
		
		requestSpecification.contentType(ContentType.JSON);
		requestSpecification.body("{\r\n"
				+ "    \"firstname\" : \"Jim\",\r\n"
				+ "    \"lastname\" : \"Brown\",\r\n"
				+ "    \"totalprice\" : 111,\r\n"
				+ "    \"depositpaid\" : true,\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2018-01-01\",\r\n"
				+ "        \"checkout\" : \"2019-01-01\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
				+ "}");
		//Hit request and get Response
		Response response = requestSpecification.post();
		
		//Validate Response
		ValidatableResponse validatableResponse = response.then().log().all();
		validatableResponse.statusCode(200);
	}

}
