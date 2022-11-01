package testscripts.ui;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import businessfunctions.DemoSiteFunction;
import businessfunctions.MenuFunction;
import businessfunctions.PayeesFucntion;
import constants.ApplicationConfig;
import contexts.PayeeContext;
import core.BaseTest;
import pages.ClientPage;
import pages.PayeesPage;

/*
 * @Author : Bathiya L
 * Test Scenario : Verify you can add new payee in the Payees page 
/ ===================
 */
public class TC22_VerifyVerifyUserCanAddNewPayee extends BaseTest{

	ClientPage clientPage;
	PayeesPage payeesPage;
	
	@BeforeClass
	public void initTest(){
		setupWebDriver();
		clientPage = new ClientPage(getDriver());
		payeesPage = new PayeesPage(getDriver());
		
		DemoSiteFunction.goToClientPage(getDriver(), baseURL.toString());
		MenuFunction.navigateToPayees(getDriver());
	}
	
	@Test
	public void runTest() throws InterruptedException {
		
		/*
		 * Create new Payee by calling addNewPayee() function
		 */
		PayeeContext context = generqtePayeeContext();
		PayeesFucntion.addNewPayee(getDriver(),context);
				
		/*
		 * ‘Payee added’ message is displayed, and payee is added in the list of payees 
		 */
		webDriverWait(ApplicationConfig.DEFAULT_ELEMENT_TIMEOUT).until(ExpectedConditions.textToBePresentInElement(payeesPage.notificationTooltip, "Payee added"));
		assertEquals(payeesPage.notificationTooltip.getText(), "Payee added","Assertion Fail on displaying Payee added message -> ");
		
		assertEquals(getDriver().findElement(payeesPage.getDisplayPayeeNameLocatorOfPayee(context.getPayeeName())).isDisplayed(), true,"Assertion Fail on Displaying Payee name on list");
		String fullAccountNumber= context.getAccountBank() + "-" + context.getAccountBranch() + "-" + context.getAccountNumber() + "-" + context.getAccountSuffix();
		assertEquals(getDriver().findElement(payeesPage.getDisplayAccuountLocatorOfAccount(fullAccountNumber)).isDisplayed(), true,"Assertion Fail on Displaying account on list");
		log("Verified payee is added in the list of payees");

	}
	
	/*
	 * Create Payee data context 
	 */
	private PayeeContext generqtePayeeContext() {
		PayeeContext context = new PayeeContext();
		context.setPayeeName("Zaman");
		context.setAccountBank("55");
		context.setAccountBranch("5555");
		context.setAccountNumber("5555555");
		context.setAccountSuffix("55");
		
		return context;
	}

}
