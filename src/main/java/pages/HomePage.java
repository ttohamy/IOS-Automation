package pages;

import base.PageBase;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;

public class HomePage extends PageBase {
    public HomePage(IOSDriver driver) {
        super(driver);
    }
    By activityIndicator = AppiumBy.accessibilityId("Activity Indicators");
    By sliderButton = AppiumBy.accessibilityId("Sliders");
    public void openActivityIndicator(IOSDriver driver){
        clickElement(driver, activityIndicator);
    }
    public void openSliderPage(IOSDriver driver){
        clickElement(driver,sliderButton);
    }
}
