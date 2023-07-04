package rb.suite;

import java.net.MalformedURLException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import io.appium.java_client.android.AndroidDriver;
import rb.page.RatingStarPage;
import reportG.ReportGeneratorC;
import util.BaseClass;
import util.CommonUtils;

public class RatingBarSuite extends BaseClass{

	AndroidDriver driver = null;
	ReportGeneratorC reportGenerator = null;
	CommonUtils utils = null;
	@Test
	public void f() {
		reportGenerator.logMessage("Rating Stars Test Started", Status.INFO);
		RatingStarPage rating=new RatingStarPage(driver,reportGenerator);
		try {
			rating.giveRatingStars(driver, 3);
		} catch (InterruptedException e) {
			reportGenerator.logMessage("Rating Star Test Failed", Status.FAIL);
			e.printStackTrace();
		}
		reportGenerator.logMessage("Rating Stars Test Completed", Status.INFO);
		
	}

	@BeforeTest
	public void beforeMethod() throws InterruptedException {
		reportGenerator = new ReportGeneratorC();
		utils = new CommonUtils();
		super.startAppiumServer();
		try {
			reportGenerator.setUpReportGenerator("testRatingStars");

			driver = super.getAndroidDriver();
			Thread.sleep(4500);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@AfterTest
	public void afterMethod() {
		reportGenerator.endReport();
		utils.sleepForWhile(3);
		super.closeDriver(driver);
		super.stopServer();
	}

	@BeforeSuite
	public void beforeSuite() {
		
	}

	@AfterSuite
	public void afterSuite() {
		
	}

}
