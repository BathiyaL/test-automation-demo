package businessfunctions;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import contexts.PayeeContext;
import pages.PayeesPage;

public class PayeesFucntion {

	public static void addNewPayee(WebDriver driver,PayeeContext context){
		PayeesPage payeesPage = new PayeesPage(driver);
		
		payeesPage.addPayees.click();
		payeesPage.payeeName.sendKeys(context.getPayeeName());
		payeesPage.soemoneNewOption.click();
		 
		payeesPage.accountBank.sendKeys(context.getAccountBank());
		payeesPage.accountBranch.sendKeys(context.getAccountBranch());
		payeesPage.accountNumber.sendKeys(context.getAccountNumber());
		payeesPage.accountSuffix.sendKeys(context.getAccountSuffix());
		payeesPage.addAccount.click();
		

		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.textToBePresentInElement(payeesPage.notificationTooltip, "Payee added"));
		assertEquals(payeesPage.notificationTooltip.getText(), "Payee added","Assertion Fail on displaying Payee added message -> ");
	}
}
