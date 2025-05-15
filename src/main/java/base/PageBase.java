package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class PageBase {
    protected IOSDriver driver ;
    public Logger logger = LogManager.getLogger(getClass());

    public PageBase(IOSDriver driver){
        this.driver = driver;
    }
    public void waitBeforeInteract(IOSDriver driver, By locator) {
        FluentWait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(7)).pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class).ignoring(TimeoutException.class).withMessage(locator+" Not Found");
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
    public void sliding(AppiumDriver driver , String slidingDirection , int startPointPercentage  , int endPointPercentage , By locator){
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH , "finger");
        Sequence swipe = new Sequence(finger,1);
        Dimension size = driver.findElement(locator).getSize();
        int elementWidth = size.getWidth();
        int elementHeight = size.getHeight();
        WebElement slider = driver.findElement(locator);
        int startX = (int) (slider.getLocation().x + (elementWidth* ((double) startPointPercentage/100)));
        int endX = 0 ;
        double movementPercentage = Math.abs(((double) endPointPercentage /100) - ((double) startPointPercentage /100));
        System.out.println("movementPercentage : "+movementPercentage);
        if(slidingDirection.equalsIgnoreCase("right")) {
             endX = startX + (int) (elementWidth * movementPercentage);
        }
        else if(slidingDirection.equalsIgnoreCase("left")) {
            endX = startX -  (int) (elementWidth * movementPercentage);
            System.out.println("StartX : "+startX + " endX : "+ endX);
        }else {
            throw new IllegalArgumentException("Direction must be 'LEFT' or 'RIGHT'");
        }
        int centerY = slider.getLocation().y + elementHeight/2;
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(),startX,centerY));
        // 0 is the standard for touch pointer down //use this finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()) instead of 0 if testing web
        swipe.addAction(finger.createPointerDown(0));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(),endX , centerY));
        // 0 is the standard for touch pointer down
        swipe.addAction(finger.createPointerUp(0));
        driver.perform(Arrays.asList(swipe));
    }
    public String getTextFromField(IOSDriver driver, By locator){
        try{
            waitBeforeInteract(driver,locator);
            System.out.println(driver.findElement(locator).getText());
            return driver.findElement(locator).getText();
        }catch (Exception e){
            System.out.println("Exception : can not retrieve Element Text");
            return e.getMessage();
        }
    }
    public void waitAlertToBePresent(IOSDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
    }
    public void switchToAlertAndDoAction(IOSDriver driver , String action){
        waitAlertToBePresent(driver);
        Alert alert = driver.switchTo().alert();
        if(action.equalsIgnoreCase("accept")){
            alert.accept();
        } else if(action.equalsIgnoreCase("dismiss")){
            alert.dismiss();
        } else {
            throw new IllegalArgumentException("Action Must Be Accept or Dismiss");
        }
    }
    public void switchToAlertAndSelectOption(IOSDriver driver ,By buttonsLocator ,int option){
        waitAlertToBePresent(driver);
        List<WebElement> options = driver.findElements(buttonsLocator);
        System.out.println("all options : "+options.size());
        try {
            options.get(option).click();
        }catch (IndexOutOfBoundsException e ){
           logger.info("You are out of the index your index should be from 0 to"+(options.size()-1));
           e.printStackTrace();
        }
    }
    public void switchToAlertAndAddText(IOSDriver driver , By textAreaLocator , @Nullable By submitButtonLocator, String text ) {
        waitAlertToBePresent(driver);
        Alert alert = driver.switchTo().alert();
        addTextToField(driver,textAreaLocator,text);
        if(submitButtonLocator != null ){
            if((driver.findElement(submitButtonLocator).isEnabled())){
                logger.info("Submit button is enabled. Text length is accepted. PAlert will be accepted.");
                alert.accept();
            }else {
                logger.info("Submit button is disabled. Text length is not accepted. Alert will be dismissed.");
                alert.dismiss();
            }
        }else{
            logger.info("No validation required. Proceeding to accept alert.");
            alert.accept();
        }
    }
    public void scrollWheel(IOSDriver driver , By elementLocator ){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Map<String, Object> params = new HashMap<>();
        params.put("order", "next"); // or "previous" --> Direction to scroll
        params.put("offset", 0.1);  // try with 0.1 or 0.2 if needed Scroll strength small = finer control
        params.put("element", ((RemoteWebElement) driver.findElement(elementLocator)).getId()); // assuming 0 is the hour
        js.executeScript("mobile: selectPickerWheelValue", params);
    }

}
