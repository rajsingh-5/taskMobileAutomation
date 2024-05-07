package base;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.appium.java_client.Setting;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import utils.FileUtility;
import utils.PropertyUtils;

public class BaseTest {
	public static FileUtility fLib = new FileUtility();
	public SoftAssert softAssert = new SoftAssert();
	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement> driver;
	public String currentRunningMethod;

	ExtentSparkReporter spark;
	public ExtentReports report;
	public static ExtentTest test;
	public static String className;

	@BeforeSuite
	public void beforeSuite() {
	}

	@BeforeClass
	public void configBc(ITestContext context) throws Throwable {
		LogManager.getLogger().info("Runnig Before Class");
		className = this.getClass().getSimpleName();
	}

	@BeforeMethod
	public void configBm(ITestResult result) throws Throwable {

		currentRunningMethod = result.getMethod().getMethodName();
		spark = new ExtentSparkReporter("./Extent Report/" + dateForReport() + "/" + className + "/"
				+ currentRunningMethod + dateTime() + ".html");
		spark.config().setDocumentTitle("General Store");
		spark.config().setReportName(className + " Automation Execution report");
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("os", System.getProperty("os.name"));

		test = report.createTest(result.getMethod().getMethodName());
		System.out.println("starting: " + result.getMethod().getMethodName());
		LogManager.getLogger().info("starting: " + result.getMethod().getMethodName());

		capabilities();
		driver.activateApp(PropertyUtils.getTestConfiguration("appPackage"));
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		driver.setSetting(Setting.WAIT_FOR_IDLE_TIMEOUT, 2);
	}

	@AfterMethod
	public void configAm(ITestResult result) throws Exception {
		LogManager.getLogger().info(
				"---------------------------Execution of the Method is Finished--------------------------------------\n \n");
		report.flush();
		driver.quit();
	}

	public static AndroidDriver<AndroidElement> capabilities() throws IOException, InterruptedException {

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", PropertyUtils.getTestConfiguration("platformName"));
		cap.setCapability("platformVersion", PropertyUtils.getTestConfiguration("platformVersion"));
		cap.setCapability("deviceName", PropertyUtils.getTestConfiguration("deviceName"));
		cap.setCapability("udid", PropertyUtils.getTestConfiguration("udid"));
		cap.setCapability("automationName", PropertyUtils.getTestConfiguration("automationName"));
		cap.setCapability("appPackage", PropertyUtils.getTestConfiguration("appPackage"));
		cap.setCapability("appActivity", PropertyUtils.getTestConfiguration("appActivity"));
		cap.setCapability("newCommandTimeout", 90000);
		cap.setCapability("noReset", true);
		cap.setCapability("clearSystemFiles", true);
		cap.setCapability("waitForIdleTimeout", 10);
		URL url = new URL("http://0.0.0.0:4723/wd/hub");
		driver = new AndroidDriver<AndroidElement>(url, cap);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;
	}

	public String dateTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("_dd-MM-yyyy_HHmmss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

	public String dateForReport() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}
}
