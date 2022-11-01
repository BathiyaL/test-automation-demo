package testscripts.api;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.net.URISyntaxException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import constants.APIEndPoints;
import core.BaseTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/*
 * @Author : Bathiya L
 * Test Scenario : 
 * Verify GET Users request
 * Assert status code is 200
 * Assert users count is 10
 */
public class TC1_VerifyGETUsersRequest extends BaseTest{
	
	private final int EXPCTED_USER_COUNT = 10;	
	private String responseBody;

	String requestURI;
	
	@BeforeClass
	public void initTest() throws URISyntaxException {
		requestURI = baseURL.resolve(APIEndPoints.USERS).toString();
		log("Request endpoint : " + requestURI);	
	}

	@Test
	public void runTest() {

		Response response = given().log().uri()
								.when()
								.get(requestURI);
		
		responseBody = response.body().asPrettyString();
		assertEquals(response.statusCode(), 200,"Assert status code is 200 -> ");
		log("Verify Status Code: " + response.statusCode());

		JsonPath jsonPathEvaluator = response.jsonPath();
		int actualUserCount = jsonPathEvaluator.getInt("$.size()");
		assertEquals(actualUserCount, EXPCTED_USER_COUNT, "Users Count verification fail :  Assert \"User Count\" is "+ EXPCTED_USER_COUNT +" -> ");		
		log("Verify User Count is : " + actualUserCount);
		
	}
	
	@AfterClass
	void teadDownTest(){
		log("### Tear down test ..........");
		log("### Response Body ###   \n \n " + responseBody + "\n");
	}
}
