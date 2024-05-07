package pageObjects;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductPage extends BasePage {
	public ProductPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(xpath = "//android.widget.TextView[@text=\"$110.0\"]/following-sibling::android.widget.TextView")
	public AndroidElement btnAddToCartForPG3;
	
	@FindBy(xpath = "//android.widget.TextView[@text=\"$110.0\"]")
	public AndroidElement txtPG3Amt;
	
	@FindBy(xpath = "//android.widget.TextView[@text=\"$116.97\"]/following-sibling::android.widget.TextView")
	public AndroidElement btnAddToCartForNikeSFBJungle;
	
	@FindBy(xpath = "//android.widget.TextView[@text=\"$116.97\"]")
	public AndroidElement txtNikeSFBJungleAmt;
	
	@FindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	public AndroidElement btnCart;

	public void scrollToProductPG3() {
		scrollWithUiAutomator("text", "PG 3");
	}

	public void clickAddToCartForPG3() throws Throwable {
		click(btnAddToCartForPG3, "ADD TO CART For PG3");
	}
	
	public void clickAddToCartForNikeSFBJungle() throws Throwable {
		click(btnAddToCartForNikeSFBJungle, "ADD TO CART For Nike SFB Jungle");
	}
	
	
	public void scrollToProductNikeSFBJungle() {
		scrollWithUiAutomator("text", "Nike SFB Jungle");
	}
	
	public String fetchValuePG3() {
		String amt = getText(txtPG3Amt);
		return amt;
	}
	public String fetchValueNikeSFBJungle() {
		String amt = getText(txtNikeSFBJungleAmt);
		return amt;
	}
	
	public void clickCartBtn() throws Throwable {
		click(btnCart, "Cart Icon");
	}
}
