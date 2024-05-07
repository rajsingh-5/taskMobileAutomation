package pageObjects;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class WebPage extends BasePage {
	public WebPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@FindBy(xpath = "//android.widget.EditText")
	public AndroidElement editfield;
	
	public void sendKeysOnSearchBox() throws Throwable {
		sendKeys(editfield, "General Store");
	}
	
}
