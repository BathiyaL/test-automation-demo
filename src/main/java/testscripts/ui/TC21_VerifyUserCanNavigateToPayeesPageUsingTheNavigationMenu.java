package testscripts.ui;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import businessfunctions.DemoSiteFunction;
import businessfunctions.MenuFunction;
import constants.ApplicationConfig;
import core.BaseTest;
import pages.ClientPage;
import pages.PayeesPage;

/*
 * @Author : Bathiya L
 * Test Scenario : Navigate to Payees page and Verify page is successfully loaded 
 */
public class TC21_VerifyUserCanNavigateToPayeesPageUsingTheNavigationMenu extends BaseTest{

	ClientPage clientPage;
	PayeesPage payeesPage;
	
	@BeforeClass
	public void initTest(){
		setupWebDriver();
		clientPage = new ClientPage(getDriver());
		payeesPage = new PayeesPage(getDriver());
	}

	@Test
	public void runTest(){
				
		/*
		 * Go to base URL and navigate to Payees page
		 */
		DemoSiteFunction.goToClientPage(getDriver(), baseURL.toString());
		
		MenuFunction.navigateToPayees(getDriver());
	
		/*
		 * Verify Payees page is loaded by asserting page header
		 */
		webDriverWait(ApplicationConfig.DEFAULT_ELEMENT_TIMEOUT).until(ExpectedConditions.visibilityOfAllElements(payeesPage.pageHeading));
		assertEquals(payeesPage.pageHeading.getText(), "Payees","Assertion Fail on page heading validation -> ");		
		log("Verified Payees Page is loaded");
	}

}
