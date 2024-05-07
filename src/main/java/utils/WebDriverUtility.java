package utils;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * 
 * @author Biswajit
 *
 */
public class WebDriverUtility {
	public WebDriverWait wait;

	public WebDriverUtility(AndroidDriver<AndroidElement> driver) {
		this.wait = new WebDriverWait(driver, 30);
	}

	public void waitForElemnetToBeVisible(AndroidElement elemnet) {
		wait.until(ExpectedConditions.visibilityOf(elemnet));
	}


}
