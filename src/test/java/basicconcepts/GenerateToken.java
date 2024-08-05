package basicconcepts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class GenerateToken {

	// In this test case we don't pass base URI as we are executing this in Test
	// We are getting Base URI from UseIfDefaultVariable class
	// We are overwrite the value of basePath
	
	@Test
	public void generateToken() {		
		RestAssured
			.given()
			.log()
			.all()
			.basePath("/auth")
			.contentType(ContentType.JSON)
			.body("{\r\n"
					+ "    \"username\" : \"admin\",\r\n"
					+ "    \"password\" : \"password123\"\r\n"
					+ "}")
			.post()
			.then()
			.log()
			.all()
			.statusCode(200);
	}

}
