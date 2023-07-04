package rb.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import reportG.ReportGeneratorC;
import util.CommonUtils;

public class RatingStarPage {
	AndroidDriver d;
	ReportGeneratorC reportGenerator;
	CommonUtils utils = new CommonUtils();

	@FindBy(id = "android:id/button1")
	public WebElement initialOKButton;

	@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.ListView/android.widget.TextView[11]")
	public WebElement ViewsOption;

	public RatingStarPage(AndroidDriver driver, ReportGeneratorC report) {
		this.d = driver;
		this.reportGenerator = report;

		PageFactory.initElements(driver, this);
	}

	public void giveRatingStars(AndroidDriver driver, int value) throws InterruptedException {
		Thread.sleep(5000);
		initialOKButton.click();
		Assert.assertTrue(ViewsOption.isDisplayed());
		reportGenerator.logAndCaptureScreenshot(driver, "HomePage", "Views Option Displayed", Status.PASS);
		ViewsOption.click();
		reportGenerator.logMessage("ViewsOption clicked", Status.PASS);

		utils.scrollAndClick(driver, "Rating Bar");
		utils.sleepForWhile(2);

		boolean check = driver.findElement(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView"))
				.isDisplayed();
		Assert.assertTrue(check);

		WebElement bar2 = driver.findElement(AppiumBy.id("com.hmh.api:id/ratingbar2"));
		Point p = bar2.getLocation();
		System.out.println(p.getX() + " " + p.getY());

		// Swipe to give rating
		int sWidth = p.getX(), sHeight = p.getY(), eWidth = p.getX(), eHeight = p.getY()+value;

		try {

			TouchAction action = new TouchAction(driver);
			action.press(PointOption.point(sWidth, sHeight)).waitAction().moveTo(PointOption.point(sWidth, eHeight))
					.release().perform();

		} catch (Exception ex) {
			System.out.println("   ########## " + ex.toString());
		}

	}
}
