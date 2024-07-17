package restfulbooker;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class GenerateToken {

	@Test
	public void generateToken() {		
		RestAssured
			.given()
			.log()
			.all()
			.baseUri("https://restful-booker.herokuapp.com")
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
