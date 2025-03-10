package contacts_app_pages;

import base.PageBase;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;

public class AddContactPage extends PageBase {
    By firstNameLocator = AppiumBy.accessibilityId("First name");
    By lastNameLocator = AppiumBy.accessibilityId("Last name");
    By doneLocator = AppiumBy.accessibilityId("Done");
    By shareContactLocator = AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`name == \"Share Contact\"`]");

    public AddContactPage(IOSDriver driver) {
        super(driver);
    }
    public void addContactInfo(IOSDriver driver , String firstName , String lastName ){
        addTextToField(driver , firstNameLocator , firstName);
        addTextToField(driver , lastNameLocator , lastName);
        clickElement(driver,doneLocator);
    }
    public boolean isShareContactButtonAppears(IOSDriver driver){
        try {
            waitBeforeInteract(driver,shareContactLocator);
            return driver.findElement(shareContactLocator).isDisplayed();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
