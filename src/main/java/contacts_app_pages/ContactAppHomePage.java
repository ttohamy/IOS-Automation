package contacts_app_pages;

import base.PageBase;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;

public class ContactAppHomePage extends PageBase {
    public ContactAppHomePage(IOSDriver driver) {
        super(driver);
    }
    By addButton = AppiumBy.accessibilityId("Add");

    public void openAddContactPage(IOSDriver driver){
        clickElement(driver,addButton);
    }
}
