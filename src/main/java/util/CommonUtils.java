package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;

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
}
