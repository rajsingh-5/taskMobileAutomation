package pageObjects;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends BasePage {
	
	public CartPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@FindBy(xpath = "//*[@text=\"PG 3\"]/following-sibling::android.widget.LinearLayout/android.widget.TextView")
	public AndroidElement txtPG3Amt;
	
	@FindBy(xpath = "//*[@text=\"Nike SFB Jungle\"]/following-sibling::android.widget.LinearLayout/android.widget.TextView")
	public AndroidElement txtNikeSFBJunlgeAmt;
	
	@FindBy(xpath = "//android.widget.CheckBox")
	public AndroidElement btnCheckBox;
	
	@FindBy(id = "com.androidsample.generalstore:id/btnProceed")
	public AndroidElement btnCompletePurchase;
	
	
	public String fetchValueOnCartPagePG3() {
		String value = getText(txtPG3Amt);
		return value;
	}
	public String fetchValueOnCartPageNikeSFBJunlge() {
		String value = getText(txtNikeSFBJunlgeAmt);
		return value;
	}
	
	public void clickbtnCheckBox() throws Throwable {
		click(btnCheckBox, "Check Box");
	}
	public void clickbtnCompletePurchase() throws Throwable {
		click(btnCompletePurchase, "Visit to the website to complete purchase");
	}
}
