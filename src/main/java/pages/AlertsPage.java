package pages;

import base.PageBase;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;

public class AlertsPage extends PageBase {
    By simpleButton = AppiumBy.accessibilityId("Simple");
    By okayCancelButton = AppiumBy.accessibilityId("Okay / Cancel");
    By otherButton = AppiumBy.accessibilityId("Other");
    By allButtons = AppiumBy.xpath("//XCUIElementTypeAlert[@name=\"A Short Title Is Best\"]//XCUIElementTypeButton");
    By textEntryAlertButton = AppiumBy.accessibilityId("Text Entry");
    By secureTextEntryButton = AppiumBy.accessibilityId("Secure Text Entry");
    By alertTextArea = AppiumBy.xpath("//XCUIElementTypeTextField");
    By secureAlertSubmitButton = AppiumBy.accessibilityId("OK");
    By secureAlertTextArea= AppiumBy.iOSClassChain("**/XCUIElementTypeAlert[`name == \"A Short Title Is Best\"`]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeSecureTextField");
    public AlertsPage(IOSDriver driver) {
        super(driver);
    }

    public void openSimpleAlert(IOSDriver driver){
        clickElement(driver,simpleButton);
    }
    public void openOkayCancelAlert(IOSDriver driver){
        clickElement(driver,okayCancelButton);
    }
    public void openOtherAlert(IOSDriver driver){
        clickElement(driver,otherButton);
    }
    public void openTextEntryAlert(IOSDriver driver){
        clickElement(driver,textEntryAlertButton);
    }
    public void openSecureTextEntryAlert(IOSDriver driver){
        clickElement(driver,secureTextEntryButton);
    }

    public void interactWithSimpleAlert(IOSDriver driver){
        openSimpleAlert(driver);
        switchToAlertAndDoAction(driver,"accept");
    }
    public void interactWithOkayCancelAlert(IOSDriver driver){
        openOkayCancelAlert(driver);
        switchToAlertAndDoAction(driver,"dismiss");
    }
    public void interactWithOtherAlert(IOSDriver driver){
        openOtherAlert(driver);
        switchToAlertAndSelectOption(driver,allButtons,2);
    }
    public void interactWithTextAreaAlert(IOSDriver driver){
        openTextEntryAlert(driver);
        switchToAlertAndAddText(driver,alertTextArea,null,"Adding some text to alert! ");
    }
    public void interactWithSecureTextEntryAlert(IOSDriver driver){
        openSecureTextEntryAlert(driver);
        switchToAlertAndAddText(driver,secureAlertTextArea,secureAlertSubmitButton,"Adding");
    }

}
