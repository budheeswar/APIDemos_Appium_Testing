package reportG;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportGeneratorC {
	
	private ExtentHtmlReporter htmlReporter = null;
	private ExtentReports extent = null;
	private ExtentTest test = null;
	private static final String BASE_REPORT_DIR = System.getProperty("user.dir");
	private String reportPath = null;
	
	public void setUpReportGenerator(String methodName) {
		htmlReporter = new ExtentHtmlReporter(BASE_REPORT_DIR+"/"+methodName+".html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		htmlReporter.config().setDocumentTitle("Android App Testing Report"); // Title of Report
		htmlReporter.config().setReportName("Android App Testing  Report"); // Name of the report
		htmlReporter.config().setTheme(Theme.STANDARD);// Default Theme of Report

		// General information related to application
		extent.setSystemInfo("Application Name", "Appium");
		extent.setSystemInfo("Author", "R Buddeeswar");
		test = extent.createTest(methodName);
		
	}
	public void logMessage(String message, Status logLevel) {
		Status level = Status.INFO;
		if (logLevel != null)
			level = logLevel;
		test.log(level, message);
		//Reporter.log(message); // routing the message to testng reporter
	}
	public void endReport() {
		extent.flush();
	}

}
