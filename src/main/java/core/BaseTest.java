package core;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import constants.ApplicationConfig;

/*
 * @Author : Bathiya L
 * All tests extends BaseTest class.
 * Functionalities that common to all tests classes can implement here
 */
public class BaseTest {

	public URI baseURL;
	// public static WebDriver driver;
	protected static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal();

	private Logger logger = null;

	public String requestedTestBrowser;

	public void log(String logMessage) {
		logger = LogManager.getLogger("Log");
		logger.info(logMessage);
	}

	/*
	 * @Author : Bathiya L
	 * 
	 * @Paremeter : baseurlParameter If base url empty, Null, or not passed from xml
	 * Test Plan, this will use the defined default URL
	 */
	private void bindBaseURL(String baseurlParameter) throws URISyntaxException {
		if (baseurlParameter == null || baseurlParameter.isEmpty()
				|| baseurlParameter.contains(ProgramConstants.TESTNG_PARM_VALUE_NOT_FOUND_MSG)) {
			baseURL = new URI(ApplicationConfig.DEFAULT_BASE_URL);
			log("baseURL parameter values is null, empty or not passed from a Test Plan hence bind to the default base url");
		} else {
			baseURL = new URI(baseurlParameter);
		}
		log("Base URL : " + baseURL);
	}

	public void setupWebDriver() {
		threadLocalDriver.set(BrowserManager.setupBrowser(requestedTestBrowser));
		if(requestedTestBrowser.equals(TestBrowsers.CHROME)) {log("Chrome Launched .....");}
		else if(requestedTestBrowser.equals(TestBrowsers.FIREFOX)) {log("Firefox Launched .....");}
	}

	public static WebDriver getDriver() {
		return threadLocalDriver.get();
	}

	@Parameters({ "baseURL", "browser" })
	@BeforeTest(alwaysRun = true)
	public void initTest(@Optional(ProgramConstants.TESTNG_PARM_VALUE_NOT_FOUND_MSG) String baseurlParm,
			@Optional(ApplicationConfig.DEFAULT_Browser) String browserParm) throws URISyntaxException {
		log("Staring new Test with ...." );
		bindBaseURL(baseurlParm);		
		requestedTestBrowser = browserParm;
	}

	public WebDriverWait webDriverWait(int durationInSeconds) {
		return new WebDriverWait(getDriver(), Duration.ofSeconds(durationInSeconds));
	}

	@AfterTest
	public void tearDown() {

		if (getDriver() != null) {
			getDriver().quit();
		}
		threadLocalDriver.remove();
		log("Teardown test ....");
	}

}
