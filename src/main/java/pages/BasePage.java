package pages;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	protected static WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(xpath="//div[@class='ApplicationLoading-message']")
    public WebElement applicationLoadingSpinner;
    
    public boolean isElementPresent(By locatorKey) {
        try {
            driver.findElement(locatorKey);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
    
	public WebDriverWait webDriverWait(int durationInSeconds) {
		return new WebDriverWait(driver, Duration.ofSeconds(durationInSeconds));
	}
	
	/*
	 * invoke click action using JavascriptExecutor
	 */
	public void forceClick(WebElement elment) {
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", elment);
	}
  
}
