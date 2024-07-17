package basicconcepts;

import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MeasureResponseTime {

	@Test
	public void differentWaysToResponseTime() {
		
		Response response = RestAssured
			.given()
			.log()
			.all()
			.baseUri("https://restful-booker.herokuapp.com")
			.basePath("/booking")
			.contentType(ContentType.JSON)
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
			.post();
		
		long responseTimeInMS = response.time();
		System.out.println("Response Time in milliseconds " + responseTimeInMS);
		
		long responseTimeInSec = response.timeIn(TimeUnit.SECONDS);
		System.out.println("Response Time in seconds " + responseTimeInSec);

		long responseTimeInMS1 = response.getTime();
		System.out.println("Response Time in milliseconds 1 " + responseTimeInMS1);
		
		long responseTimeInSec1 = response.getTimeIn(TimeUnit.SECONDS);
		System.out.println("Response Time in seconds 1 " + responseTimeInSec1);
		
		//Assertion
		
		response.then().time(Matchers.lessThan(5000L));
		response.then().time(Matchers.greaterThan(1000L));
		
		response.then().time(Matchers.both(Matchers.greaterThan(500L))
									 .and(Matchers.lessThan(4000L)));
		
		response.then().time(Matchers.both(Matchers.lessThan(5L)).and(Matchers.greaterThanOrEqualTo(1L)), TimeUnit.SECONDS);
	}

}
