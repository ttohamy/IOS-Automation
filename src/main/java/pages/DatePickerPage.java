package pages;

import base.DriverManager;
import helper.ElementsHelper;
import helper.LoggerHelper;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DatePickerPage {
//    By datePickerButton = AppiumBy.accessibilityId("Date and Time Picker");
    By datePickerButton = AppiumBy.iOSNsPredicateString("label MATCHES '\\\\d{1,2} [A-Za-z]+ \\\\d{4}'");
    By daysButton = AppiumBy.xpath("//XCUIElementTypeButton");
//    By timePickerButton = AppiumBy.xpath("//XCUIElementTypeButton//XCUIElementTypeButton[2]");
    By timePickerButton = AppiumBy.iOSNsPredicateString("label MATCHES '[0-9]{1,2}:[0-9]{2}.*[AP]M'");
    By dismissDatePickerPopupButton = AppiumBy.accessibilityId("PopoverDismissRegion");
    By hoursWheel = AppiumBy.iOSNsPredicateString("value MATCHES '[0-9]{1,2} o’clock'");
    By minutesWheel = AppiumBy.iOSNsPredicateString("value MATCHES '[0-9]{1,2} minutes'");
    By wheels = AppiumBy.xpath("//XCUIElementTypePickerWheel");
    private IOSDriver driver;
    private ElementsHelper elementsAction ;
    private LoggerHelper loggerHelper ;


    public DatePickerPage(IOSDriver driver) {
        this.driver = driver;
        this.elementsAction = new ElementsHelper(driver);
        this.loggerHelper = new LoggerHelper();
    }

    public void openDatePicker(){
        elementsAction.clickElement(datePickerButton);
    }
    public void openTimePicker(){
        elementsAction.clickElement(timePickerButton);
    }
    public void selectSpecificDate(){
        openDatePicker();
        List<WebElement> allDays = DriverManager.getDriver().findElements(daysButton);
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
        loggerHelper.logger.info(allDays.get(nextValueToBeClicked).getDomAttribute("name"));
        dismissDatePickerPopup();

    }
    public void selectSpecificTime() throws InterruptedException {
        openTimePicker();
        List<WebElement> timeWheels = DriverManager.getDriver().findElements(wheels);
        elementsAction.scrollWheel(hoursWheel);
        int expectedMinutes = Integer.valueOf(getMinutesInSpecificFormat());
        //As time have only even number here we check if the number is odd will add to it 1
        if(expectedMinutes%2!=0){
            System.out.println("Minutes is odd " + expectedMinutes);
            expectedMinutes = expectedMinutes +1;
            System.out.println("Minutes Now should be even "+expectedMinutes);
        }
        timeWheels.get(2).sendKeys(getLocalTimeAMPMFormat());
        Thread.sleep(500);
        timeWheels.get(1).sendKeys(String.valueOf(expectedMinutes));
        dismissDatePickerPopup();
    }
    public String getLocalTimeAMPMFormat(){
        LocalTime localTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("a");
        return localTime.format(formatter);
    }
    public String getMinutesInSpecificFormat(){
        LocalTime localTime = LocalTime.now();
        DateTimeFormatter minutesFormater = DateTimeFormatter.ofPattern("mm");
        LocalTime minutesAfterTime = localTime.plusMinutes(3);
        return minutesAfterTime.format(minutesFormater);
    }

    public void dismissDatePickerPopup(){
        elementsAction.clickElement(dismissDatePickerPopupButton);
    }

}
