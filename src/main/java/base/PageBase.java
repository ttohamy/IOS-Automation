package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

public class PageBase {
    protected IOSDriver driver ;
    Logger logger = LogManager.getLogger(this);

    public PageBase(IOSDriver driver){
        this.driver = driver;
    }
    public void waitBeforeInteract(IOSDriver driver, By locator) {
        FluentWait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(7)).pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class).ignoring(TimeoutException.class).withMessage("Element NOT Found");
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void clickElement(IOSDriver driver , By locator){
            waitBeforeInteract(driver,locator);
            driver.findElement(locator).click();
    }
    public void addTextToField(IOSDriver driver , By locator,String text){
            waitBeforeInteract(driver,locator);
            driver.findElement(locator).sendKeys(text);
    }
    public void sliding(AppiumDriver driver , String startSlidingDirection , double startPointPercentage , By locator){
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH , "finger");
        Sequence swipe = new Sequence(finger,1);
        Dimension size = driver.findElement(locator).getSize();
        int elementWidth = size.getWidth();
        int elementHeight = size.getHeight();
        WebElement slider = driver.findElement(locator);
        int startX = slider.getLocation().x + (int)(elementWidth* startPointPercentage);
        int endX = 0 ;
        if(startSlidingDirection.equalsIgnoreCase("right")) {
             endX = startX + (int) (elementWidth * 0.20);
        }
        else if(startSlidingDirection.equalsIgnoreCase("left")) {
            endX = startX -  (int) (elementWidth * 0.20);
        }else {
            throw new IllegalArgumentException("Direction must be 'LEFT' or 'RIGHT'");
        }
        int centerY = slider.getLocation().y + elementHeight/2;
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(),startX,centerY));
        // 0 is the standard for touch pointer down //use this finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()) instead of 0 if testing web
        swipe.addAction(finger.createPointerDown(0));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(),endX , centerY));
        // 0 is the standard for touch pointer down
        swipe.addAction(finger.createPointerDown(0));
        driver.perform(Arrays.asList(swipe));


    }

}
