package pages;

import base.PageBase;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatePickerPage extends PageBase {
//    By datePickerButton = AppiumBy.accessibilityId("Date and Time Picker");
    By datePickerButton = AppiumBy.iOSNsPredicateString("label MATCHES '\\\\d{1,2} [A-Za-z]+ \\\\d{4}'");
    By daysButton = AppiumBy.xpath("//XCUIElementTypeButton");
//    By timePickerButton = AppiumBy.xpath("//XCUIElementTypeButton//XCUIElementTypeButton[2]");
    By timePickerButton = AppiumBy.iOSNsPredicateString("label MATCHES '[0-9]{1,2}:[0-9]{2}.*[AP]M'");
    By dismissDatePickerPopupButton = AppiumBy.accessibilityId("PopoverDismissRegion");
    By hoursWheel = AppiumBy.iOSNsPredicateString("value MATCHES '[0-9]{1,2} o’clock'");
    By minutesWheel = AppiumBy.iOSNsPredicateString("value MATCHES '[0-9]{1,2} minutes'");
    By wheels = AppiumBy.xpath("//XCUIElementTypePickerWheel");

    public DatePickerPage(IOSDriver driver) {
        super(driver);
    }
    public void openDatePicker(IOSDriver driver){
        clickElement(driver,datePickerButton);
    }
    public void openTimePicker(IOSDriver driver){
        clickElement(driver, timePickerButton);
    }
    public void selectSpecificDate(IOSDriver driver){
        openDatePicker(driver);
        List<WebElement> allDays = driver.findElements(daysButton);
        int nextValueToBeClicked = 0;
        for (int i = 0; i < allDays.size(); i++) {
            WebElement day = allDays.get(i);
            if (day.isEnabled()) {
                String value = day.getDomAttribute("value");
                if (value != null && value.equalsIgnoreCase("1")) {
                    nextValueToBeClicked = i+1;
                }
            }
        }
        allDays.get(nextValueToBeClicked).click();
        logger.info(allDays.get(nextValueToBeClicked).getDomAttribute("name"));
        dismissDatePickerPopup(driver);

    }
    public void selectSpecificTime(IOSDriver driver){
        openTimePicker(driver);
        List<WebElement> timeWheels = driver.findElements(wheels);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Map<String, Object> params = new HashMap<>();
        params.put("order", "next"); // or "previous" --> Direction to scroll
        params.put("offset", 0.1);  // try with 0.1 or 0.2 if needed Scroll strength small = finer control
        params.put("element", ((RemoteWebElement) timeWheels.get(0)).getId()); // assuming 0 is the hour
        js.executeScript("mobile: selectPickerWheelValue", params);
        timeWheels.get(2).sendKeys("PM");
        timeWheels.get(1).sendKeys("56");
        dismissDatePickerPopup(driver);
    }
    public void dismissDatePickerPopup(IOSDriver driver){
        clickElement(driver,dismissDatePickerPopupButton);
    }

}
