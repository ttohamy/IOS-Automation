package pages;

import base.DriverManager;
import helper.ElementsHelper;
import helper.LoggerHelper;
import helper.WaitHelper;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;

public class SteppersPage {
    private ElementsHelper elementAction ;
    private WaitHelper waitHelper;
    private LoggerHelper loggerHelper;
    By defaultStepperIncrement = AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"Increment\"`][1]");
    By defaultStepperDecrement= AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"Decrement\"`][1]");
    By stepCounter = AppiumBy.iOSNsPredicateString("label MATCHES '[0-9]{1,2}'");

    public SteppersPage() {
        this.elementAction = new ElementsHelper(DriverManager.getDriver());
        waitHelper = new WaitHelper(DriverManager.getDriver());
        loggerHelper = new LoggerHelper();
    }
    public void incrementStepper(){
        if(elementAction.isElementEnabled(defaultStepperIncrement)) {
            elementAction.clickElement(defaultStepperIncrement);
            loggerHelper.logger.info("Increment button is enabled");
        }else {
            loggerHelper.logger.info("Increment button is disabled");
        }
    }
    public void decrementStepper(){
        if(elementAction.isElementEnabled(defaultStepperDecrement)) {
            elementAction.clickElement(defaultStepperDecrement);
            loggerHelper.logger.info("Decrement button is enabled");
        }else {
            loggerHelper.logger.error("Can not interact with Decrement button");
            throw new ElementNotInteractableException("Decrement button is disabled");
        }
    }
    public void increaseWithAmount(int amount){
        waitHelper.waitBeforeInteract(defaultStepperIncrement);
        int initialValue = 0;
        while (initialValue < amount){
            incrementStepper();
            initialValue++;
        }
    }
    public boolean isIncreaseButtonEnabled(){
        return elementAction.isElementEnabled(defaultStepperIncrement);
    }
    public String getStepCounter(){
        return elementAction.getTextFromField(stepCounter);
    }

}
