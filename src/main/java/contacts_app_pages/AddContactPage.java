package contacts_app_pages;

import helper.ElementsHelper;
import helper.WaitHelper;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;

public class AddContactPage{
    By firstNameLocator = AppiumBy.accessibilityId("First name");
    By lastNameLocator = AppiumBy.accessibilityId("Last name");
    By doneLocator = AppiumBy.accessibilityId("Done");
    By shareContactLocator = AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`name == \"Share Contact\"`]");
    private IOSDriver driver;
    private ElementsHelper elementsAction ;
    private WaitHelper wait ;

    public AddContactPage(IOSDriver driver) {
        this.driver = driver;
        this.elementsAction = new ElementsHelper(driver);
        wait = new WaitHelper(driver);
    }
    public void addContactInfo(IOSDriver driver , String firstName , String lastName ){
        elementsAction.addTextToField( firstNameLocator , firstName);
        elementsAction.addTextToField(lastNameLocator , lastName);
        elementsAction.clickElement(doneLocator);
    }
    public boolean isShareContactButtonAppears(IOSDriver driver){
        try {
            wait.waitBeforeInteract(shareContactLocator);
            return driver.findElement(shareContactLocator).isDisplayed();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
