package testCases;

import java.util.Set;

import org.testng.annotations.Test;

import base.BasePage;
import base.BaseTest;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import pageObjects.CartPage;
import pageObjects.HomePage;
import pageObjects.ProductPage;
import pageObjects.WebPage;
import utils.PropertyUtils;

public class Journey extends BaseTest {

	@Test
	public void generalStoreApp() throws Throwable {
		HomePage homePage = new HomePage(driver);
		homePage.selectCountryDropDown();
		homePage.scrollToCountryIndia(PropertyUtils.getTestData("countryName"));
		homePage.selectCountryAsIndia(PropertyUtils.getTestData("countryName"));
		homePage.enterUsername(PropertyUtils.getTestData("username"));
		homePage.clickBtnLetsShop();
		ProductPage productPage = new ProductPage(driver);
		productPage.scrollToProductNikeSFBJungle();
		productPage.clickAddToCartForPG3();
		Thread.sleep(1000);
		productPage.clickAddToCartForNikeSFBJungle();
		String pg3AmtOnProductPage = productPage.fetchValuePG3();
		String nikeAmtOnProductPage = productPage.fetchValueNikeSFBJungle();
		float sumOfAmtOnProductPage = Float.parseFloat(pg3AmtOnProductPage.replace("$", ""))
				+ Float.parseFloat(nikeAmtOnProductPage.replace("$", ""));

		productPage.clickCartBtn();

		CartPage cartPage = new CartPage(driver);

		String pg3AmtOnCartPage = cartPage.fetchValueOnCartPagePG3();
		String nikeAmtOnCartPage = cartPage.fetchValueOnCartPageNikeSFBJunlge();

		float sumOfAmtOnCartPage = Float.parseFloat(pg3AmtOnCartPage.replace("$", ""))
				+ Float.parseFloat(nikeAmtOnCartPage.replace("$", ""));

		BasePage.validate(sumOfAmtOnProductPage, sumOfAmtOnCartPage, "Amount Matched");
		cartPage.clickbtnCheckBox();
		cartPage.clickbtnCompletePurchase();

		WebPage webPage = new WebPage(driver);
		Set<String> contexts = BaseTest.driver.getContextHandles();
		System.out.println(contexts);
		for (String contextName : contexts) {
			System.out.println(contexts);
			try {
				driver.context(contextName);
				webPage.sendKeysOnSearchBox();
				break;
			} catch (Exception e) {
			}
		}
		BaseTest.driver.pressKey(new KeyEvent(AndroidKey.BACK));
		homePage.verifyUserNameField();
	}
}
