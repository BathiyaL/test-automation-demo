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
import pages.PaymentsPage;

/*
 * @Author : Bathiya L
 * Test Scenario : Navigate to Payments page and verify payment transfer
 */
public class TC25_VerifyPaymentTransfer extends BaseTest{

	ClientPage clientPage;
	PayeesPage payeesPage;
	PaymentsPage paymentPage;
	
	@BeforeClass
	public void initTest() throws InterruptedException{
		setupWebDriver();
		clientPage = new ClientPage(getDriver());
		payeesPage = new PayeesPage(getDriver());
		paymentPage = new PaymentsPage(getDriver());
		
		DemoSiteFunction.goToClientPage(getDriver(), "https://www.demo.bnz.co.nz/client");
	}
	
	@Test
	public void runTest() throws InterruptedException {
		
		// variables to hold test data 
		String fromAccount = "Everyday";
		String toAccount = "Bills";
		float billsAccountBalanceBefore;
		float billsAccountBalanceAfter;
		float everydayAccountBalanceBefore;
		float everydayAccountBalanceAfter;
		float transferAmount = 500;

		/*
		 * capture before account balance
		 */
		billsAccountBalanceBefore = clientPage.getAccountBalanceOf("Bills");
		log("Bill's Account Balance before transaction = " + billsAccountBalanceBefore);
		everydayAccountBalanceBefore = clientPage.getAccountBalanceOf("Everyday");
		log("Everyday's Account Balance before transaction = " + everydayAccountBalanceBefore);
		log("Transfer Amount from Everyday to Bill's Account = " + transferAmount);
		
		/*
		 * Navigate to Payment page and make a money transfer
		 */
		MenuFunction.navigateToPayments(getDriver());
		paymentPage.fromAccount.click();
		paymentPage.searchAndSelectAccountOf(fromAccount);		
		webDriverWait(ApplicationConfig.DEFAULT_ELEMENT_TIMEOUT).until(ExpectedConditions.invisibilityOf(paymentPage.fromAccountsContainer));	
		webDriverWait(ApplicationConfig.DEFAULT_ELEMENT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(paymentPage.toAccount)).click();
		paymentPage.clickOnAccountTab();
		paymentPage.searchAndSelectAccountOf(toAccount);
		webDriverWait(ApplicationConfig.DEFAULT_ELEMENT_TIMEOUT).until(ExpectedConditions.invisibilityOf(paymentPage.toAccountsContainer));
		
		paymentPage.amountTextBox.sendKeys(String.valueOf(transferAmount));		
		paymentPage.transferButton.click();
		
		/*
		 * Verify transfer is successful by asserting successful message
		 */
		webDriverWait(ApplicationConfig.DEFAULT_ELEMENT_TIMEOUT).until(ExpectedConditions.textToBePresentInElement(payeesPage.notificationTooltip, "Transfer successful"));
		assertEquals(payeesPage.notificationTooltip.getText(), "Transfer successful","Assertion Fail on displaying Transfer successful message -> ");
		
		/*
		 * Verify account balances are update correctly after the transaction
		 */
		billsAccountBalanceAfter = clientPage.getAccountBalanceOf(toAccount);
		everydayAccountBalanceAfter = clientPage.getAccountBalanceOf(fromAccount);
		
		assertEquals(billsAccountBalanceAfter, (billsAccountBalanceBefore+transferAmount),"Assertion Fail on validating Bills Account balance -> ");
		log("Verified Bills account balance is correct. New Balance = " + billsAccountBalanceAfter);
		assertEquals(everydayAccountBalanceAfter, (everydayAccountBalanceBefore-transferAmount),"Assertion Fail on validating Everyday Account balance -> ");
		log("Verified Evryday account balance is correct. New Balance = " + everydayAccountBalanceAfter);

	}
}