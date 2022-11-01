package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PayeesPage extends BasePage {
	public PayeesPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h1[@class='CustomPage-heading']/span")
	public WebElement pageHeading;
	
	@FindBy(xpath = "//button[@aria-label='Add payee']/span[text()='Add']")
	public WebElement addPayees;
	
	
	@FindBy(id = "ComboboxInput-apm-name")
	public WebElement payeeName;
	
	
	
	@FindBy(xpath = "//span[contains(text(),'Someone new:')]")
	public WebElement soemoneNewOption;
	
		
	@FindBy(id = "apm-bank")
	public WebElement accountBank;
	
	@FindBy(id = "apm-branch")
	public WebElement accountBranch;
	
	@FindBy(id = "apm-account")
	public WebElement accountNumber;
	
	@FindBy(id = "apm-suffix")
	public WebElement accountSuffix;
		
	@FindBy(xpath = "//button[text()='Add']")
	public WebElement addAccount;
		
	@FindBy(xpath = "//div[@id='notification']/div/span")
	public WebElement notificationTooltip;
	
	@FindBy(xpath = "//input[@placeholder='Search payees']")
	public WebElement searchPayees;
	
	public By getDisplayPayeeNameLocatorOfPayee(String payeeName) {
		String locator = "//span[@class='js-payee-name' and text()='%s']";
		return By.xpath(String.format(locator, payeeName));
	}
	
	public By getDisplayAccuountLocatorOfAccount(String account) {
		String locator = "//p[@class='Avatar-text js-payee-account' and text()='%s']";		
		return By.xpath(String.format(locator, account));
	}
	
	public List<WebElement> getPayeesNameList() {
		return driver.findElements(By.xpath("//span[@class='js-payee-name']"));		
	}
	
	public WebElement getPayeeNameRowOfPayee(String payeeName) {
		String locator = "//span[text()='%s']";
		return driver.findElement(By.xpath(String.format(locator, payeeName)));
	}
	
	
	
	@FindBy(xpath = "//div[contains(@class,'tooltip-panel')]")
	public WebElement toolTipPannelDiv;
	
	private final String errorHeaderLocator = "//div[@id='modal-form-manager']//div[@class='error-header']";
	@FindBy(xpath = errorHeaderLocator)
	public WebElement errorHeader;	
	public By errorHeaderByLocator = By.xpath(errorHeaderLocator);
	
	
	@FindBy(xpath = "//h3[contains(@aria-label,'Sort by payee name A to Z selected')]")
	public WebElement payeesNameHeader;
	
	
}
