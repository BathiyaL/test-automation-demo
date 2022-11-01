package testscripts.ui;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.collect.Ordering;

import businessfunctions.DemoSiteFunction;
import businessfunctions.MenuFunction;
import businessfunctions.PayeesFucntion;
import contexts.PayeeContext;
import core.BaseTest;
import pages.ClientPage;
import pages.PayeesPage;

/*
 * @Author : Bathiya L
 * Test Scenario : Validate errors 
 */
public class TC24_VerifyThatPayeesCanBeSortedByName extends BaseTest{

	ClientPage clientPage;
	PayeesPage payeesPage;
	PayeeContext payeeContext;
	
	@BeforeClass
	public void initTest() throws InterruptedException{
		setupWebDriver();
		clientPage = new ClientPage(getDriver());
		payeesPage = new PayeesPage(getDriver());
		
		/*
		 * Add new Payee as a precondition data
		 */
		DemoSiteFunction.goToClientPage(getDriver(), baseURL.toString());	
		MenuFunction.navigateToPayees(getDriver());	
		payeeContext = generqtePayeeContext();
		PayeesFucntion.addNewPayee(getDriver(),generqtePayeeContext());
	}
	
	@Test
	public void runTest() throws InterruptedException {

		/*
		 * Verify list is sorted in ascending order by default
		 */
		List<WebElement> defaultList = payeesPage.getPayeesNameList();	
		
		List<String> defaultNameList = new ArrayList<String>();		
		for(WebElement elm : defaultList ) {			
			defaultNameList.add(elm.getText());
		}
			
		assertTrue(defaultNameList.contains(payeeContext.getPayeeName()),"Assertion Fail since newly aded payee is not in the list -> ");
		assertTrue(Ordering.natural().isOrdered(defaultNameList),"Assert Fail on validating default ascending order");
		log("Verified payee list is sorted in ascending order by default ");
		
		
		/*
		 * Verify list is sorted in descending order after click on Name header
		 */
		
		List<String> actualDescendingNameList = new ArrayList<String>();
		List<String> expectedDescendingNameList = new ArrayList<String>();
		
		
		payeesPage.payeesNameHeader.click();		
		List<WebElement> actualDescendingList = payeesPage.getPayeesNameList();	
		for(WebElement elm : actualDescendingList ) {
			actualDescendingNameList.add(elm.getText());
		}
				
		expectedDescendingNameList.addAll(actualDescendingNameList);		
		Collections.sort(expectedDescendingNameList,Collections.reverseOrder());// explicitly descending the captured list as expected value
		
		assertTrue(actualDescendingNameList.equals(expectedDescendingNameList),"Assert Fail on validating descending order");
		log("Verified payee list is sorted in descending order after click on Name header ");
		
	}
	
	/*
	 * Create Payee data context 
	 */
	private PayeeContext generqtePayeeContext() {
		PayeeContext context = new PayeeContext();
		context.setPayeeName("Zaman");
		context.setAccountBank("55");
		context.setAccountBranch("5555");
		context.setAccountNumber("5555555");
		context.setAccountSuffix("55");
		
		return context;
	}

}

