package base;

import io.appium.java_client.ios.IOSDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
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

}
