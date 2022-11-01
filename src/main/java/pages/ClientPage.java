package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import constants.ApplicationConfig;

public class ClientPage extends BasePage{

	public ClientPage(WebDriver driver) {
		super(driver);		
	}

	@FindBy(xpath="//span[text()='Menu']/../../..")
    public WebElement menu;

    @FindBy(id="lastname")
    private WebElement lastName;

    @FindBy(xpath="//a[@href=\"/client/payees\"]")
    public WebElement payeesMenu;

    @FindBy(id="zipcode")
    private WebElement zipCode;

    @FindBy(id="signup")
    private WebElement submitButton;
      
    @FindBy(xpath="//span[text()='Pay or transfer']")
    public WebElement payOrTransferMenu;
    
	@FindBy(xpath = "//div[@id='notification']/div/span")
	public WebElement notificationTooltip;
    
	public float getAccountBalanceOf(String accountName) {
		String locator = "//h3[contains(text(),'%s')]/parent::span/following-sibling::span[@class='account-balance']";
		WebElement elm = driver.findElement(By.xpath(String.format(locator, accountName)));
		return Float.valueOf(elm.getText().trim().replace(",", ""));
	}
	public void waitUntillLoadingSpinnerDisappear() {
		webDriverWait(ApplicationConfig.DEFAULT_ELEMENT_TIMEOUT).until(ExpectedConditions.invisibilityOf(applicationLoadingSpinner));	
	}
	
	

}
