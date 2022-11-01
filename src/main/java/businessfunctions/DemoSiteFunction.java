package businessfunctions;

import org.openqa.selenium.WebDriver;

import pages.ClientPage;

public class DemoSiteFunction {
	public static void goToClientPage(WebDriver driver, String baseURL) {
		ClientPage clientPage = new ClientPage(driver);		
		driver.get(baseURL.toString());
		clientPage.waitUntillLoadingSpinnerDisappear();
	}
}
