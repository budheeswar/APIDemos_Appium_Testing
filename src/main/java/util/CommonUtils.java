package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;

public class CommonUtils {
	public Properties getConfigFile() {

		FileInputStream file = null;
		Properties properties = new Properties();
		String dir = System.getProperty("user.dir");
		try {
			// Provide the path to your properties file
			file = new FileInputStream(dir + "/src/main/resources/config.properties");
			properties.load(file);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Close the input stream
			if (file != null) {
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return properties;
	}

	public void sleepForWhile(int seconds) {
		try {
			int milliSeconds = (seconds * 1000);
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			System.out.println("Got Exception while ");
			e.printStackTrace();
		}
	}

	public void scrollAndClick(AndroidDriver driver,String visibleText) {
	     WebElement option=driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+visibleText+"\").instance(0))"));
		 option.click();
	}
	public void scrollTillVisible(AndroidDriver driver,String visibleText) {
	     WebElement option=driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+visibleText+"\").instance(0))"));
	}
	public void performSwipeAction(String direction, AndroidDriver driver) throws Exception {

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
