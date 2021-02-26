package Base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.cucumber.java.Scenario;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {
    public static AppiumDriver<MobileElement> driver;

    public Base() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void launchApp() throws IOException {
        File config = new File("src/test/resources/config.properties");
        FileInputStream inputStream = new FileInputStream(config);
        Properties properties = new Properties();
        properties.load(inputStream);

        URL appiumServerURL = new URL(properties.getProperty("appiumURL"));

        DesiredCapabilities caps = new DesiredCapabilities();
        if (properties.getProperty("platformName").equalsIgnoreCase("Android")) {
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, properties.getProperty("deviceName"));
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, properties.getProperty("platformName"));
            caps.setCapability("appPackage", properties.getProperty("appPackage"));
            caps.setCapability("appActivity", properties.getProperty("appActivity"));
            driver = new AndroidDriver<MobileElement>(appiumServerURL, caps);
        } else if (properties.getProperty("platformName").equalsIgnoreCase("iOS")) {
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, properties.getProperty("deviceName"));
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, properties.getProperty("platformName"));
            caps.setCapability("appPackage", properties.getProperty("appPackage"));
            caps.setCapability("appActivity", properties.getProperty("appActivity"));
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
            driver = new IOSDriver<MobileElement>(appiumServerURL, caps);
        }
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
    }

    public void startRecording() {
        ((CanRecordScreen) driver).startRecordingScreen();
    }

    public void stopRecording(String scenarioName) {
        String media = ((CanRecordScreen) driver).stopRecordingScreen();
        String dirPath = "Reports" + File.separator + "Videos";
        File videoDir = new File(dirPath);
        synchronized (videoDir) {
            if (!videoDir.exists()) {
                videoDir.mkdirs();
            }
        }
        try (FileOutputStream stream = new FileOutputStream(videoDir + File.separator + scenarioName + ".mp4")) {
            stream.write(Base64.decodeBase64(media));
        } catch (Exception e) {
        }
    }

    public void getScreenShot(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            File screenShot = driver.getScreenshotAs(OutputType.FILE);
            String path = "Reports/" + "Screenshots/" + scenario.getName() + "_" + scenario.getLine() + ".png";
            FileUtils.copyFile(screenShot, new File(path));
        }
    }
}
