package reportG;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;

import io.appium.java_client.android.AndroidDriver;
import util.CommonUtils;

public class ReportGeneratorC {
	CommonUtils util = new CommonUtils();
	Properties config = util.getConfigFile();

	private ExtentHtmlReporter htmlReporter = null;
	private ExtentReports extent = null;
	private ExtentTest test = null;
	private static final String BASE_REPORT_DIR = System.getProperty("user.dir");
	private String reportPath = null;

	public void setUpReportGenerator(String methodName) {
		htmlReporter = new ExtentHtmlReporter(BASE_REPORT_DIR + "/" + methodName + ".html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		htmlReporter.config().setDocumentTitle("Android App Testing Report"); // Title of Report
		htmlReporter.config().setReportName("Android App Testing  Report"); // Name of the report
		htmlReporter.config().setTheme(Theme.STANDARD);// Default Theme of Report

		// General information related to application
		extent.setSystemInfo("Application Name", "Appium Testing");
		extent.setSystemInfo("Author", "R Buddeeswar");
		test = extent.createTest(methodName);

	}

	public void logMessage(String message, Status logLevel) {
		Status level = Status.INFO;
		if (logLevel != null)
			level = logLevel;
		test.log(level, message);
		// Reporter.log(message); // routing the message to testng reporter
	}

	public void logAndCaptureScreenshot(AndroidDriver driver, String methodName, String message, Status logLevel) {
		if (config.getProperty("isScreenShot").equalsIgnoreCase("True")) { // if isScreenShot value is false, then
			// ignore screen shot
			captureScreenShot(driver, methodName);
		}
		logMessage(message, logLevel);
	}

	private void captureScreenShot(AndroidDriver driver, String methodName) {
		System.out.println("Taking Screenshot of  " + methodName + " ");

		String imgPath = System.getProperty("user.dir")+"/target/screenshots/" + methodName + ".jpg";
		File dest = new File(imgPath);
		File source = driver.getScreenshotAs(OutputType.FILE);
		try {
			Files.move(source, dest);
		} catch (IOException e) {
			System.out.println("Unable to Take Screenshot");
			e.printStackTrace();
		}
		System.out.println("Screenshot Captured Successfully");

	}

	public void endReport() {
		extent.flush();
	}

}
