package base;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import utils.ReporterClass;
import utils.WebDriverUtility;

public class BasePage {
	public WebDriverUtility wLib = new WebDriverUtility(BaseTest.driver);

	public void click(AndroidElement element, String elementName) throws Throwable {
		try {
			wLib.waitForElemnetToBeVisible(element);
			element.click();
			ReporterClass.ReportLoggerScreenshot(ReporterClass.pass, "\"" + elementName + "\" is clicked successfully");
			LogManager.getLogger().info(elementName + " is clicked");
		} catch (Exception e) {
			LogManager.getLogger().info(elementName + " unable to click");
			ReporterClass.ReportLoggerScreenshot(ReporterClass.info, elementName + " unable to click");
			throw e;
		}
	}

	public void scrollWithUiAutomator(String attributeName, String attributeValue) {
		BaseTest.driver.getContext();
		WebElement element = ((AndroidDriver<AndroidElement>) BaseTest.driver).findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector()." + attributeName
						+ "(\"" + attributeValue + "\").instance(0))");
		String name = element.getAttribute("text");
		ReporterClass.ReportLoggerScreenshot(ReporterClass.pass, "Scroll till \"" + name + "\" and its visible.");
	}

	public void sendKeys(AndroidElement element, String txt) {
		wLib.waitForElemnetToBeVisible(element);
		try {
			element.sendKeys(txt);
			ReporterClass.ReportLoggerScreenshot(ReporterClass.pass, "text \"" + txt + "\" is entered in text box");
			LogManager.getLogger().info("text " + txt + " is entered in text box");
		} catch (Exception e) {
			BaseTest.test.info("send Keys " + txt + "  is failed");
			LogManager.getLogger().info("send Keys " + txt + " is failed");
			Assert.fail();
		}
	}

	public boolean isDisplayed(AndroidElement element) {
		try {
			new WebDriverWait(BaseTest.driver, 10).until(ExpectedConditions.visibilityOf(element));
			String name = element.getAttribute("text");
			ReporterClass.ReportLoggerScreenshot(ReporterClass.pass,
					"text \"" + name + "\" is displayed successfully.");
			LogManager.getLogger().info(name + " is displayed successfully.");
			return element.isDisplayed();
		} catch (NoSuchElementException | TimeoutException exception) {
			BaseTest.test.fail("Element is not present");
			return false;
		}
	}

	public String getText(AndroidElement element) {
		wLib.waitForElemnetToBeVisible(element);
		String name = element.getAttribute("text");
		LogManager.getLogger().info(name + " is fetched successfully.");
		return name;
	}

	public static void validate(Object expected, Object actual, String assertionFor) {

		try {
			logFile(assertionFor, actual, expected);
			Assert.assertEquals(actual, expected, assertionFor);
			ReporterClass.pass("<b> <u>" + assertionFor + "</u> </b>   |   <b><i>Actual: </i> </b>" + actual
					+ " and <b><i> Expected: </i> </b>" + expected, true);
			LogManager.getLogger()
					.info(" - " + assertionFor + "  |   Actual: " + actual + " and  Expected: " + expected, true);

		} catch (AssertionError assertionError) {
			ReporterClass.fail("<b> <u>" + assertionFor + "   |   <b><i>Actual: </i> </b>" + actual
					+ " and <b><i> Expected: </i> </b>" + expected, true);
			LogManager.getLogger()
					.info(" - " + assertionFor + "   |   Actual: " + actual + " and  Expected: " + expected, true);
			Assert.fail(assertionFor);
		}
	}

	private static void logFile(String assertionFor, Object actual, Object expected) {
		Reporter.log("Checking:" + assertionFor, true);
		Reporter.log("Expected: " + expected, true);
		Reporter.log("Actual: " + actual, true);
	}

}
