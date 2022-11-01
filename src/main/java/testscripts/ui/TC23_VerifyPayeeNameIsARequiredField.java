package testscripts.ui;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import businessfunctions.DemoSiteFunction;
import businessfunctions.MenuFunction;
import core.BaseTest;
import pages.ClientPage;
import pages.PayeesPage;

/*
 * @Author : Bathiya L
 * Test Scenario : Verify payee name is a required field
/ ===================
 */
public class TC23_VerifyPayeeNameIsARequiredField extends BaseTest{

	ClientPage clientPage;
	PayeesPage payeesPage;
	
	@BeforeClass
	public void initTest(){
		setupWebDriver();
		clientPage = new ClientPage(getDriver());
		payeesPage = new PayeesPage(getDriver());

		/*
		 * Navigate to Payees page 
		 */
		DemoSiteFunction.goToClientPage(getDriver(), baseURL.toString());	
		MenuFunction.navigateToPayees(getDriver());
	}
	
	@Test
	public void runTest() throws InterruptedException {

		
		String populatePayeeName = "AA MASTERCARD";
		String errorHeaderMessage = "A problem was found. Please correct the field highlighted below.";
		String tooltipMessage = "Payee Name is a required field. Please complete to continue.";
		
		/*
		 * Validate errors both tooltip and error header
		 */
		payeesPage.addPayees.click();
		payeesPage.addAccount.click();
				
		assertEquals(payeesPage.toolTipPannelDiv.getText(), tooltipMessage,"Assertion Fail when validating tooltip message -> ");
		assertEquals(payeesPage.toolTipPannelDiv.isDisplayed(), true,"Assertion Fail since validation tooltip not display -> ");
		log("Validted tooltip is displayed with expected message");
		

		assertEquals(payeesPage.errorHeader.getText(), errorHeaderMessage,"Assertion Fail when validating error header message -> ");
		log("Validted error header message is displayed with expected message");
		
		/*
		 * Populate mandatory fields and Validate errors are gone 
		 */
		payeesPage.payeeName.sendKeys(populatePayeeName);
		payeesPage.getPayeeNameRowOfPayee(populatePayeeName).click();
		
		assertEquals(payeesPage.toolTipPannelDiv.isDisplayed(), false,"Assertion Fail since error validation tooltip sitll display after data poulate -> ");
		log("Validted tooltip is gone after valid data population");
				
		assertEquals(payeesPage.isElementPresent(payeesPage.errorHeaderByLocator), false,"Assertion Fail since error header message sitll display after data poulate -> ");
		log("Validted Error header is gone after valid data population");

	}

}
