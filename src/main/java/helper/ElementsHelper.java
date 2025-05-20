package helper;

import base.DriverManager;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElementsHelper {
    private IOSDriver driver;
    private WaitHelper wait ;
    private LoggerHelper loggerHelper;

    public ElementsHelper(IOSDriver driver) {
        this.driver = driver;
        this.wait = new WaitHelper(driver);
        this.loggerHelper = new LoggerHelper();
    }
    public void clickElement(By locator){
        wait.waitBeforeInteract(locator);
        DriverManager.getDriver().findElement(locator).click();
    }
    public void addTextToField(By locator,String text){
        wait.waitBeforeInteract(locator);
        DriverManager.getDriver().findElement(locator).sendKeys(text);
    }
    public void sliding( String slidingDirection , int startPointPercentage  , int endPointPercentage , By locator){
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH , "finger");
        Sequence swipe = new Sequence(finger,1);
        Dimension size = DriverManager.getDriver().findElement(locator).getSize();
        int elementWidth = size.getWidth();
        int elementHeight = size.getHeight();
        WebElement slider = DriverManager.getDriver().findElement(locator);
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
        DriverManager.getDriver().perform(Arrays.asList(swipe));
    }
    public String getTextFromField(By locator){
        try{
            wait.waitBeforeInteract(locator);
            loggerHelper.logger.info("getTextFromField "+locator+" : "+DriverManager.getDriver().findElement(locator).getText());
            return DriverManager.getDriver().findElement(locator).getText();
        }catch (Exception e){
            loggerHelper.logger.error("Exception : can not retrieve Element Text");
            return e.getMessage();
        }
    }
    public void waitAlertToBePresent(){
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
    }
    public void switchToAlertAndDoAction( String action){
        waitAlertToBePresent();
        Alert alert = DriverManager.getDriver().switchTo().alert();
        if(action.equalsIgnoreCase("accept")){
            alert.accept();
        } else if(action.equalsIgnoreCase("dismiss")){
            alert.dismiss();
        } else {
            throw new IllegalArgumentException("Action Must Be Accept or Dismiss");
        }
    }
    public void switchToAlertAndSelectOption(By buttonsLocator ,int option){
        waitAlertToBePresent();
        List<WebElement> options = DriverManager.getDriver().findElements(buttonsLocator);
        System.out.println("all options : "+options.size());
        try {
            options.get(option).click();
        }catch (IndexOutOfBoundsException e ){
            loggerHelper.logger.info("You are out of the index your index should be from 0 to"+(options.size()-1));
            e.printStackTrace();
        }
    }
    public void switchToAlertAndAddText( By textAreaLocator , @Nullable By submitButtonLocator, String text ) {
        waitAlertToBePresent();
        Alert alert = DriverManager.getDriver().switchTo().alert();
        addTextToField(textAreaLocator,text);
        if(submitButtonLocator != null ){
            if((DriverManager.getDriver().findElement(submitButtonLocator).isEnabled())){
                loggerHelper.logger.info("Submit button is enabled. Text length is accepted. PAlert will be accepted.");
                alert.accept();
            }else {
                loggerHelper.logger.info("Submit button is disabled. Text length is not accepted. Alert will be dismissed.");
                alert.dismiss();
            }
        }else{
            loggerHelper.logger.info("No validation required. Proceeding to accept alert.");
            alert.accept();
        }
    }
    public void scrollWheel( By elementLocator ){
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("order", "next"); // or "previous" --> Direction to scroll
        params.put("offset", 0.1);  // try with 0.1 or 0.2 if needed Scroll strength small = finer control
        params.put("element", ((RemoteWebElement) DriverManager.getDriver().findElement(elementLocator)).getId()); // assuming 0 is the hour
        js.executeScript("mobile: selectPickerWheelValue", params);
    }
    public boolean isElementEnabled(By locator){
        return DriverManager.getDriver().findElement(locator).isEnabled();
    }

}
