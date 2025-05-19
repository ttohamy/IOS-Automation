package pages;

import helper.ElementsHelper;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;

public class AlertsPage  {
    By simpleButton = AppiumBy.accessibilityId("Simple");
    By okayCancelButton = AppiumBy.accessibilityId("Okay / Cancel");
    By otherButton = AppiumBy.accessibilityId("Other");
    By allButtons = AppiumBy.xpath("//XCUIElementTypeAlert[@name=\"A Short Title Is Best\"]//XCUIElementTypeButton");
    By textEntryAlertButton = AppiumBy.accessibilityId("Text Entry");
    By secureTextEntryButton = AppiumBy.accessibilityId("Secure Text Entry");
    By alertTextArea = AppiumBy.xpath("//XCUIElementTypeTextField");
    By secureAlertSubmitButton = AppiumBy.accessibilityId("OK");
    By secureAlertTextArea= AppiumBy.iOSClassChain("**/XCUIElementTypeAlert[`name == \"A Short Title Is Best\"`]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeSecureTextField");
    IOSDriver driver;
    public ElementsHelper elementsAction ;
    public AlertsPage(IOSDriver driver) {
       this.driver = driver;
       this.elementsAction = new ElementsHelper(driver);
    }

    public void openSimpleAlert(IOSDriver driver){
        elementsAction.clickElement(driver,simpleButton);
    }
    public void openOkayCancelAlert(IOSDriver driver){
        elementsAction.clickElement(driver,okayCancelButton);
    }
    public void openOtherAlert(IOSDriver driver){
        elementsAction.clickElement(driver,otherButton);
    }
    public void openTextEntryAlert(IOSDriver driver){
        elementsAction.clickElement(driver,textEntryAlertButton);
    }
    public void openSecureTextEntryAlert(IOSDriver driver){
        elementsAction.clickElement(driver,secureTextEntryButton);
    }

    public void interactWithSimpleAlert(IOSDriver driver){
        openSimpleAlert(driver);
        elementsAction.switchToAlertAndDoAction(driver,"accept");
    }
    public void interactWithOkayCancelAlert(IOSDriver driver){
        openOkayCancelAlert(driver);
        elementsAction.switchToAlertAndDoAction(driver,"dismiss");
    }
    public void interactWithOtherAlert(IOSDriver driver){
        openOtherAlert(driver);
        elementsAction.switchToAlertAndSelectOption(driver,allButtons,2);
    }
    public void interactWithTextAreaAlert(IOSDriver driver){
        openTextEntryAlert(driver);
        elementsAction.switchToAlertAndAddText(driver,alertTextArea,null,"Adding some text to alert! ");
    }
    public void interactWithSecureTextEntryAlert(IOSDriver driver){
        openSecureTextEntryAlert(driver);
        elementsAction.switchToAlertAndAddText(driver,secureAlertTextArea,secureAlertSubmitButton,"Adding");
    }

}
