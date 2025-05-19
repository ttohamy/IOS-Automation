package pages;

import helper.ElementsHelper;
import helper.WaitHelper;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;

public class SlidingPage {
    By slider = AppiumBy.xpath("//XCUIElementTypeSlider[1]");
    By tintedSlider = AppiumBy.xpath("(//XCUIElementTypeSlider[@value=\"50%\"])[1]");
    private IOSDriver driver;
    private ElementsHelper elementsAction ;
    private WaitHelper wait ;
    public SlidingPage(IOSDriver driver) {
        this.driver = driver;
        this.elementsAction = new ElementsHelper(driver);
        wait = new WaitHelper(driver);
    }
    public void slideRight(IOSDriver driver) throws InterruptedException {
        wait.waitBeforeInteract(driver,slider);
        elementsAction.sliding(driver, "right",42,100,slider);

    }
    public void slideLeft(IOSDriver driver) throws InterruptedException {
        wait.waitBeforeInteract(driver,slider);
        elementsAction.sliding(driver, "left",99,20,slider);
    }
    public void slideRightForTinted(IOSDriver driver){
        wait.waitBeforeInteract(driver,tintedSlider);
        elementsAction.sliding(driver, "right",50,100,tintedSlider);
    }
    public String getCurrentSliderValue(IOSDriver driver){
        return elementsAction.getTextFromField(driver, slider);
    }

}
