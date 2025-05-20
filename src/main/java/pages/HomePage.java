package pages;

import helper.ElementsHelper;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;

public class HomePage {
    private IOSDriver driver;
    private ElementsHelper elementsAction ;
    public HomePage(IOSDriver driver) {
        this.driver = driver;
        this.elementsAction = new ElementsHelper(driver);
    }
    By activityIndicator = AppiumBy.accessibilityId("Activity Indicators");
    By sliderButton = AppiumBy.accessibilityId("Sliders");
    By alertsButton = AppiumBy.accessibilityId("Alert Views");
    By datePickerButton = AppiumBy.accessibilityId("Date Picker");
    By steppersButton = AppiumBy.accessibilityId("Steppers");
    public void openActivityIndicator(){
        elementsAction.clickElement( activityIndicator);
    }
    public void openSliderPage(){
        elementsAction.clickElement(sliderButton);
    }
    public void openAlertsPage(){
        elementsAction.clickElement(alertsButton);
    }
    public void openDatePickerPage(){
        elementsAction.clickElement(datePickerButton);
    }
    public void openSteppersPage(){
        elementsAction.clickElement(steppersButton);
    }

}
