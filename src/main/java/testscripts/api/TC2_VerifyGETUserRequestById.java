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
 * Verify GET Users by id=8
 * Assert user name of user id=8
 */
public class TC2_VerifyGETUserRequestById extends BaseTest{
	
	private final int EXPCTED_ID = 8;
	private final String EXPCTED_NAME = "Nicholas Runolfsdottir V";
	
	String requestEndpoint;
	private String responseBody;

	@BeforeClass
	public void initTest() throws URISyntaxException {
		requestEndpoint = baseURL.toString().concat(APIEndPoints.USERS_BY_ID);
		log("Request endpoint : " + requestEndpoint);		
	}

	@Test
	public void runTest() {

		Response response = given().log().uri().log().params()
								.pathParam("userID", 8)
								.when()
								.get(requestEndpoint);
		
		responseBody = response.body().asPrettyString();
		assertEquals(response.statusCode(), 200,"Assert status code is 200 -> ");
		log("Verify Status Code = " + response.statusCode());
		

		JsonPath jsonPathEvaluator = response.jsonPath();
		
		int actualID = jsonPathEvaluator.get("id");		
		assertEquals(actualID, EXPCTED_ID, "verfication fail on id : Assert \"id\" is "+ EXPCTED_ID +" -> ");
		log("Verify user id = " + actualID);

		String actualName = jsonPathEvaluator.get("name");		
		assertEquals(actualName, EXPCTED_NAME, "verfication fail on user name with id 8 : Assert \"Name\" is "+ EXPCTED_NAME +" -> ");
		log("Verify user name with id 8 = " + actualName);

	}
	
	@AfterClass
	void teadDownTest(){
		log("### Tear down test ..........");		
		log("### Response Body ###   \n \n " + responseBody + "\n");
	}
}
