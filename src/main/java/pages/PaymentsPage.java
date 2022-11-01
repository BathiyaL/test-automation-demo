package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import constants.ApplicationConfig;

public class PaymentsPage extends BasePage{

	public PaymentsPage(WebDriver driver) {
		super(driver);		
	}

	@FindBy(xpath="//button[@data-testid='from-account-chooser']")
    public WebElement fromAccount;
	
	@FindBy(xpath="//button[@data-testid='to-account-chooser']")
    public WebElement toAccount;
	
	
	@FindBy(name ="amount")
    public WebElement amountTextBox;
		
	@FindBy(xpath ="//button//span[text()='Transfer']")
    public WebElement transferButton;
	
	
	@FindBy(xpath ="//input[@data-monitoring-label='Transfer Form Search']")
    public WebElement transferFromSearch;
	
	@FindBy(xpath ="//h2/span[text()='From']/ancestor::div[contains(@class,'chooserContainer')]")
    public WebElement fromAccountsContainer;
	
	@FindBy(xpath ="//h2/span[text()='To']/ancestor::div[contains(@class,'chooserContainer')]")
    public WebElement toAccountsContainer;
	
	//p[contains(@class,'name') and contains(text(),'Everyday')]

	public void searchAndSelectAccountOf(String accountName) throws InterruptedException {
		
		String locator = "//p[contains(@class,'name') and contains(text(),'%s')]/ancestor::button[@data-monitoring-label='Transfer Form Account Card']";
		By byRef = By.xpath(String.format(locator, accountName));
		
		transferFromSearch.sendKeys(String.valueOf(accountName));
		webDriverWait(ApplicationConfig.DEFAULT_ELEMENT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(byRef));
		forceClick(driver.findElement(byRef));
	}
	
	@FindBy(xpath ="//li[@data-testid='to-account-accounts-tab']")
    public WebElement accountTab;
		
	public void clickOnAccountTab() {
		Actions actions = new Actions(driver);
		actions.moveToElement(accountTab).click().build().perform();
	}
	
	
}
