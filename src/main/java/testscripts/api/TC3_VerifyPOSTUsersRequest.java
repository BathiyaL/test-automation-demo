package testscripts.api;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import constants.APIEndPoints;
import contexts.RESTUserContext;
import core.BaseTest;
import core.UtilFunctions;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import testdata.RESTUserObjectData;

/*
 * @Author : Bathiya L
 * Test Scenario : 
 * Verify POST new Users Object
 * Assert new id is created
 * Assert other request data is there in the response
 */
public class TC3_VerifyPOSTUsersRequest extends BaseTest{
	
	String requestEndpoint;
	private String responseBody;
	String userRequestObject;
	RESTUserContext userContext;

	@BeforeClass
	public void initTest() throws Exception {
		requestEndpoint = baseURL.toString().concat(APIEndPoints.USERS);
		log("Request endpoint : " + requestEndpoint);
		
		/*
		 * - User input data is externalise with a property file.
		 * - Read data from a property file and create the user context
		 * - user context pass as a parameter to crate the dynamic user request object 
		 */
		Properties prpertyFile = UtilFunctions.getPropertyFile("TC3UserInputData.properties");
		
		userContext = RESTUserObjectData.getSampleRESTUserContext();
		userContext.setName(prpertyFile.get("name").toString());
		userContext.setUsername(prpertyFile.get("username").toString());
		userContext.setEmail(prpertyFile.get("email").toString());
		userContext.setAddress_street(prpertyFile.get("address_street").toString());
		userContext.setAddress_city(prpertyFile.get("address_city").toString());
		userContext.setPhone(prpertyFile.get("phone").toString());
		userContext.setWebsite(prpertyFile.get("website").toString());
		userContext.setCompany_name(prpertyFile.get("company_name").toString());
		
		userRequestObject = RESTUserObjectData.getUserObjectString(userContext);
	}

	@Test
	public void runTest() {

		Response response = given().log().uri().log().body()
								.contentType("application/json")
								.body(userRequestObject)						
								.when()
								.post(requestEndpoint);
		 
		responseBody = response.body().asPrettyString();
		assertEquals(response.statusCode(), 201,"Assert status code is 201 -> ");
		log("Verify Status Code = " + response.statusCode());
		

		/*
		 * Verify response data
		 */
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		int actualID = jsonPathEvaluator.get("id");
		assertTrue(actualID>0, "New user id is not greater than 0 -> ");
		log("Verify new user is greater than 0. id  = " + actualID);

		String actualName = jsonPathEvaluator.get("name");		
		assertEquals(actualName, userContext.getName(), "Response user name mismatch");
		log("Verify user name is " + actualName); 
		
		String actualUsername = jsonPathEvaluator.get("username");		
		assertEquals(actualUsername, userContext.getUsername(), "Response username mismatch");
		log("Verify username is " + actualUsername); 
		
		String actualEmail = jsonPathEvaluator.get("email");		
		assertEquals(actualEmail, userContext.getEmail(), "Response email mismatch");
		log("Verify email is " + actualEmail); 
		
		String actualSetAddress_street = jsonPathEvaluator.get("address.street");		
		assertEquals(actualSetAddress_street, userContext.getAddress_street(), "Response address street mismatch");
		log("Verify address street  is " + actualSetAddress_street); 
		
		String actualSetAddress_city = jsonPathEvaluator.get("address.city");		
		assertEquals(actualSetAddress_city, userContext.getAddress_city(), "Response address city mismatch");
		log("Verify address city  is " + actualSetAddress_city); 

		String actualSetphone = jsonPathEvaluator.get("phone");		
		assertEquals(actualSetAddress_city, userContext.getAddress_city(), "Response phone mismatch");
		log("Verify phone is " + actualSetphone); 
		
		String actualWebsite = jsonPathEvaluator.get("website");		
		assertEquals(actualWebsite, userContext.getWebsite(), "Response website mismatch");
		log("Verify website is " + actualWebsite); 
		
		String actualCompany_name = jsonPathEvaluator.get("company.name");		
		assertEquals(actualCompany_name, userContext.getCompany_name(), "Response company name mismatch");
		log("Verify company name is " + actualCompany_name); 
	}
	
	@AfterClass
	void teadDownTest(){
		log("### Tear down test ..........");		
		log("### Response Body ###   \n \n " + responseBody + "\n");
	}

}
