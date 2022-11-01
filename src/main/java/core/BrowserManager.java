package core;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import constants.ApplicationConfig;

public class BrowserManager {

	public static WebDriver setupBrowser(String requestedTestBrowser) {
		WebDriver driver = null;
		if(requestedTestBrowser.equals(TestBrowsers.CHROME)) {
			URL path = BrowserManager.class.getResource("/drivers/chrome/chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", path.getPath());
			driver = new ChromeDriver();
		} else if(requestedTestBrowser.equals(TestBrowsers.FIREFOX)) {
			URL path = BrowserManager.class.getResource("/drivers/firefox/geckodriver.exe");
			System.setProperty("webdriver.gecko.driver",path.getPath());
			driver = new FirefoxDriver();
		}
				
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ApplicationConfig.DEFAULT_ELEMENT_TIMEOUT));
		driver.manage().window().maximize();
		
		return driver;
	}
}
