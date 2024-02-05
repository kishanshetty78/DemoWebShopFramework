package com.tricentis.demowebshop.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tricentis.demowebshop.Base.Base;
import com.tricentis.demowebshop.Pages.HomePage;
import com.tricentis.demowebshop.Pages.SearchResult;

public class SearchTest extends Base{

	public WebDriver driver;
	HomePage search;
	SearchResult sr;
	
	public SearchTest()
	{
		super();
	}
	
	@BeforeMethod		
	public void setupregpage()
	{
		driver = setUpDriverandInitializeURL(Prop.getProperty("browserName")); 
		search = new HomePage(driver);
	}
	
	@Test(priority=1)
	public void searchwithvalidProductName()
	{
		sr = search.enterProdSearch(testProp.getProperty("validProduct"));
		Assert.assertTrue(sr.getprodResultDisplayStatus(),"Search function of known product not visible");
	}
	
	@Test(priority=2)
	public void searchwithInvalidProductName()
	{
		sr = search.enterProdSearch(testProp.getProperty("invalidProduct"));	
		Assert.assertEquals(sr.getSearchProdErrMsg(),testProp.getProperty("NoProductErrMsg"),"Intended message not displayed");
	}
	
	@Test(priority=3,dependsOnMethods= {"searchwithInvalidProductName()"})
	public void searchwithoutanyProduct()
	{
		sr = search.enterNoProdValue();
		Assert.assertEquals(sr.getAlertMessage(),testProp.getProperty("AlertMsgText"),"Alert pop up message not appearing");
	}
	
	@AfterMethod
	public void teardown()
	{
		driver.quit();;
	}
}
