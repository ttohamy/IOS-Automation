package contacts_app_pages;

import helper.ElementsHelper;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;

public class ContactAppHomePage {
    public ContactAppHomePage(IOSDriver driver) {
        this.driver = driver;
        this.elementsAction = new ElementsHelper(driver);
    }
    By addButton = AppiumBy.accessibilityId("Add");
    private IOSDriver driver;
    private ElementsHelper elementsAction ;
    public void openAddContactPage(){
        elementsAction.clickElement(addButton);
    }
}
