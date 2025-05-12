package pages;

import base.PageBase;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.PointerInput;

import javax.swing.*;

public class SlidingPage extends PageBase {
    By slider = AppiumBy.xpath("//XCUIElementTypeSlider[1]");
    public SlidingPage(IOSDriver driver) {
        super(driver);
    }
    public void slideRight(IOSDriver driver) throws InterruptedException {
        waitBeforeInteract(driver,slider);
        sliding(driver, "right",0.42,slider);

    }
    public void slideLeft(IOSDriver driver) throws InterruptedException {
        waitBeforeInteract(driver,slider);
        sliding(driver, "left",0.60,slider);

    }

}
