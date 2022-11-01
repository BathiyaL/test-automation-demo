package businessfunctions;

import org.openqa.selenium.WebDriver;

import pages.ClientPage;

public class MenuFunction {

	public static void navigateToPayees(WebDriver driver) {
		ClientPage clientPage = new ClientPage(driver);		
		clientPage.menu.click();
		clientPage.payeesMenu.click();
	}
	
	public static void navigateToPayments(WebDriver driver) {
		ClientPage clientPage = new ClientPage(driver);		
		clientPage.menu.click();
		clientPage.payOrTransferMenu.click();
	}

}
