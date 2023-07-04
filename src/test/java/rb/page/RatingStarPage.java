package rb.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import io.appium.java_client.android.AndroidDriver;
import reportG.ReportGeneratorC;
import util.CommonUtils;

public class RatingStarPage {
	AndroidDriver d;
	ReportGeneratorC reportGenerator;
	CommonUtils utils = new CommonUtils();
	
	@FindBy(id = "android:id/button1")
	public WebElement initialOKButton;
	
	@FindBy(xpath= "/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.ListView/android.widget.TextView[11]")
	public WebElement ViewsOption;
	
	public RatingStarPage(AndroidDriver driver,ReportGeneratorC report ) {
		this.d=driver;
		this.reportGenerator = report;
		
		PageFactory.initElements(driver,this);
	}
	
	public void giveRatingStars(AndroidDriver driver,float value) throws InterruptedException {
		Thread.sleep(5000);
		initialOKButton.click();
        Assert.assertTrue(ViewsOption.isDisplayed());
		reportGenerator.logAndCaptureScreenshot(driver,"HomePage","Views Option Displayed", Status.PASS);
		ViewsOption.click();
		reportGenerator.logMessage("ViewsOption clicked", Status.PASS);
		
		utils.scrollAndClick(driver, "Rating Bar");
		utils.sleepForWhile(5);
		
	}
}
