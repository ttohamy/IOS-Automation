package pages;

import base.DriverManager;
import helper.ElementsHelper;
import helper.LoggerHelper;
import helper.WaitHelper;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class StackViewPage {
    private ElementsHelper elementAction ;
    private WaitHelper waitHelper;
    private LoggerHelper loggerHelper;

    By firsAddButton = AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"stepper increment\"`][2]");
    By secondAddButton = AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"stepper increment\"`][1]");
    By firstTextField = AppiumBy.iOSClassChain("**/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeTextField");
    By secondTextField = AppiumBy.iOSClassChain("**/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeTextField");
    By colorBox = AppiumBy.iOSClassChain("**/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeOther[1]");
    By decrementButton = AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"stepper decrement\"`][1]");

    public StackViewPage() {
        this.elementAction = new ElementsHelper(DriverManager.getDriver());
        this.waitHelper = new WaitHelper(DriverManager.getDriver());
        this.loggerHelper = new LoggerHelper();
    }
    public void clickAddButton(){
        elementAction.clickElement(firsAddButton);
    }
    public void clickSecondAddButton(){
        elementAction.clickElement(secondAddButton);
    }
    public void clickDecrementButton(){
        elementAction.clickElement(decrementButton);
    }
    public boolean isSecondTextFieldAppears(){
        waitHelper.waitBeforeInteract(secondTextField);
        return elementAction.isElemetDisplayed(secondTextField);
    }
    public boolean isColorBoxDisappears(){
        return elementAction.isElementDisappeared(colorBox);
    }


}
