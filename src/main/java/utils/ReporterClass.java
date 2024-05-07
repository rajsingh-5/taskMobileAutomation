package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import base.BaseTest;

public interface ReporterClass {

	public Status info = Status.INFO;
	public Status fail = Status.FAIL;
	public Status pass = Status.PASS;

	public static void ReportLoggerScreenshot(Status status, String message) {
		try {
			BaseTest.test
					.log(status, message,
							MediaEntityBuilder
									.createScreenCaptureFromBase64String(
											((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.BASE64))
									.build());
		} catch (Exception e) {
			System.out.println("Unable to take screenshot.");
		}
	}
	public static void pass(String message, boolean isScreeshotNeeded) {
		try {
			if (isScreeshotNeeded) {
				BaseTest.test.pass(message,
						MediaEntityBuilder
								.createScreenCaptureFromBase64String(
										((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.BASE64))
								.build());
			} else {
				BaseTest.test.pass(message);
			}
		} catch (WebDriverException e) {
			e.printStackTrace();
		}
	}

	public static void fail(String message, boolean isScreeshotNeeded) {
		try {
			if (isScreeshotNeeded) {
				BaseTest.test.fail(message,
						MediaEntityBuilder
								.createScreenCaptureFromBase64String(
										((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.BASE64))
								.build());
			} else {
				BaseTest.test.fail(message);
			}
		} catch (WebDriverException e) {
			e.printStackTrace();
		}
	}
}
