package util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class BaseClass {

	private static AndroidDriver driver = null;
	private static CommonUtils utils = new CommonUtils();
	private static Properties config = utils.getConfigFile();
	private static DesiredCapabilities dc;
	private static AppiumDriverLocalService server;

	public void startAppiumServer() {
		System.out.println(config.getProperty("main.js"));
		if(server == null) {
			server = new AppiumServiceBuilder().withAppiumJS(new File(config.getProperty("main.js")))
			.withIPAddress(config.getProperty("ipaddress")).withArgument(GeneralServerFlag.BASEPATH, "/wd/hub/")
			.usingPort(4723).withTimeout(Duration.ofSeconds(300)).build();
		server.start();
		}
		else {
			server.start();
		}
		
	}
	public void stopServer() {
		if(server !=null) {
			server.stop();
		}
	}

	public AndroidDriver getAndroidDriver() throws MalformedURLException {
		if (driver == null) {
			dc = this.setUpDesiredCapabilities();
			URL url = new URL(config.getProperty("appium.Server.Url"));
			driver = new AndroidDriver(url, dc);
			return driver;
		} else {
			return driver;
		}

	}

	public DesiredCapabilities setUpDesiredCapabilities() {
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("automationName", config.getProperty("automationName"));
		dc.setCapability("platformName", config.getProperty("platformName"));
		dc.setCapability("platformVersion", config.getProperty("platformVersion").toString());
		dc.setCapability("deviceName", config.getProperty("deviceName"));

		dc.setCapability("appPackage", config.getProperty("appPackage"));
		dc.setCapability("appActivity", config.getProperty("appActivity"));
		return dc;
	}

	public void closeDriver(AndroidDriver driv) {
		driv.quit();
	}

}
