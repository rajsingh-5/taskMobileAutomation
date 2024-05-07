package pageObjects;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage extends BasePage {
	public HomePage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@FindBy(id = "android:id/text1")
	public AndroidElement dropdownCountrySelect;
	
	@FindBy(xpath = "//android.widget.TextView[@text=\"India\"]")
	public AndroidElement countryNameIndia;
	
	@FindBy(xpath = "//android.widget.EditText")
	public AndroidElement txtfieldUsername;
	
	@FindBy(xpath = "//android.widget.Button")
	public AndroidElement btnLetsShop;
	
	public void selectCountryDropDown() throws Throwable {
		click(dropdownCountrySelect, "Drop Down");
	}
	
	public void scrollToCountryIndia(String countryName) {
		scrollWithUiAutomator("text", countryName);
	}
	public void selectCountryAsIndia(String countryName) throws Throwable {
		click(countryNameIndia, countryName);
	}
	
	public void enterUsername(String username) {
		sendKeys(txtfieldUsername, username);
	}
	public void clickBtnLetsShop() throws Throwable {
		click(btnLetsShop, "Lets Begin");
	}
	
	public void verifyUserNameField() {
		isDisplayed(txtfieldUsername);
	}
}

