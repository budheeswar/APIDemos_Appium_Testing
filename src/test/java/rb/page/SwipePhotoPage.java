package rb.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import reportG.ReportGeneratorC;

public class SwipePhotoPage {

	AndroidDriver d;
	ReportGeneratorC reportGenerator;
	
	@FindBy(id = "android:id/button1")
	public WebElement initialOKButton;
	
	@FindBy(xpath= "/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.ListView/android.widget.TextView[11]")
	public WebElement ViewsOption;
	
	@FindBy(xpath = "//android.widget.TextView[12]")
	public WebElement GalleryOption;
	
	@FindBy(xpath ="/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.ListView/android.widget.TextView[1]")
	public WebElement PhotosOption;
	
	//@FindBy(xpath="/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.Gallery/android.widget.ImageView[1]")
	//public WebElement FirstImg;

	public SwipePhotoPage(AndroidDriver driver, ReportGeneratorC reportG) {
		this.d = driver;
		this.reportGenerator = reportG;
        PageFactory.initElements(d, this);
	}

	public void doSwipeActions(AndroidDriver driver) throws InterruptedException {
		Thread.sleep(5000);
		initialOKButton.click();
        Assert.assertTrue(ViewsOption.isDisplayed());
		ViewsOption.click();
		reportGenerator.logMessage("View Option Clicked", Status.PASS);
		try {
			this.swipeActionMethod("DOWN", driver);
			reportGenerator.logMessage("Down Swipe Worked", Status.PASS);
			this.swipeActionMethod("UP", driver);
			reportGenerator.logMessage("Up Swipe Worked", Status.PASS);


		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// LEFT Swipe by holding element
		// driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.ListView/android.widget.TextView[12]")).click();
		Assert.assertTrue(GalleryOption.isDisplayed());
		GalleryOption.click();
		reportGenerator.logMessage("Gallery Option Displayed and Clicked", Status.PASS);

		Thread.sleep(500);
		Assert.assertTrue(PhotosOption.isDisplayed());
		PhotosOption.click();
		reportGenerator.logMessage("Photos Option Displayed and Clicked", Status.PASS);

		// BEFORE SWIPE
		WebElement FirstImg = driver.findElement(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.Gallery/android.widget.ImageView[1]"));
		
		String focus = FirstImg.getAttribute("focusable");
		Assert.assertEquals("true", focus);
		System.out.println(focus);

		Thread.sleep(900);
		// Left SWIPE
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
				((RemoteWebElement) FirstImg).getId(), "direction", "left", "percent", 0.75));

		// AFTER SWIPE
		String focus1 = FirstImg.getAttribute("focusable");
		Assert.assertEquals("false", focus1);
		reportGenerator.logMessage("Successfully Photo was Moved to Left", Status.PASS);

		System.out.println(focus1);

		Thread.sleep(5000);
		//driver.quit();

	}

	public void swipeActionMethod(String direction, AndroidDriver driver) throws Exception {

		Dimension size = driver.manage().window().getSize();

		int sWidth, sHeight, eWidth = 0, eHeight = 0;

		sWidth = size.getWidth() / 2;
		sHeight = size.getHeight() / 2;
		int border = 15;
		switch (direction.toUpperCase()) {

		case "DOWN":
			eWidth = size.getWidth() / 2;
			eHeight = border;
			break;
		case "UP":
			eWidth = size.getWidth() / 2;
			eHeight = size.getHeight() - border;
			break;
		case "RIGHT":
			eWidth = size.getWidth();
			eHeight = size.getHeight() / 2;
			break;
		case "LEFT":
			eWidth = border;
			eHeight = size.getHeight() / 2;
		default:
			throw new Exception("Invalid DIrection Entered");
		}

		try {

			TouchAction action = new TouchAction(driver);
			action.press(PointOption.point(sWidth, sHeight)).waitAction().moveTo(PointOption.point(sWidth, eHeight))
					.release().perform();

		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

}
