package rb.suite;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import io.appium.java_client.android.AndroidDriver;
import rb.page.SwipePhotoPage;
import reportG.ReportGeneratorC;
import util.BaseClass;

import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class SwipeActionsSuite extends BaseClass {
	AndroidDriver driver = null;
	ReportGeneratorC reportGenerator =null;

	@Test
	public void testSwipeGestures() {

		reportGenerator.logMessage("Test Started", Status.INFO);
		SwipePhotoPage swipe=new SwipePhotoPage(driver,reportGenerator);
		try {
			swipe.doSwipeActions(driver);
		} catch (InterruptedException e) {
			reportGenerator.logMessage("Swipe Action Failed", Status.FAIL);
			e.printStackTrace();
		}
		reportGenerator.logMessage("Test Completed", Status.INFO);
		
		
	}

	@BeforeMethod
	public void beforeMethod() throws InterruptedException {
		reportGenerator = new ReportGeneratorC();
		try {
			reportGenerator.setUpReportGenerator("testSwipeGestures");
			driver = super.getAndroidDriver();
			Thread.sleep(4500);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void afterMethod() {
		reportGenerator.endReport();
	}

	@BeforeSuite
	public void beforeSuite() {
		
	}

	@AfterSuite
	public void afterSuite() {
		super.closeDriver(driver);
	}

}
