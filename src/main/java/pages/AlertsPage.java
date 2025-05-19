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

    public void openSimpleAlert(){
        elementsAction.clickElement(simpleButton);
    }
    public void openOkayCancelAlert(){
        elementsAction.clickElement(okayCancelButton);
    }
    public void openOtherAlert(){
        elementsAction.clickElement(otherButton);
    }
    public void openTextEntryAlert(){
        elementsAction.clickElement(textEntryAlertButton);
    }
    public void openSecureTextEntryAlert(){
        elementsAction.clickElement(secureTextEntryButton);
    }

    public void interactWithSimpleAlert(){
        openSimpleAlert();
        elementsAction.switchToAlertAndDoAction("accept");
    }
    public void interactWithOkayCancelAlert(){
        openOkayCancelAlert();
        elementsAction.switchToAlertAndDoAction("dismiss");
    }
    public void interactWithOtherAlert(){
        openOtherAlert();
        elementsAction.switchToAlertAndSelectOption(allButtons,2);
    }
    public void interactWithTextAreaAlert(){
        openTextEntryAlert();
        elementsAction.switchToAlertAndAddText(alertTextArea,null,"Adding some text to alert! ");
    }
    public void interactWithSecureTextEntryAlert(){
        openSecureTextEntryAlert();
        elementsAction.switchToAlertAndAddText(secureAlertTextArea,secureAlertSubmitButton,"Adding");
    }

}
