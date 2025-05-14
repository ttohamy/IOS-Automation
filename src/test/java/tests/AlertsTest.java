package tests;

import base.TestBase;
import org.testng.annotations.Test;

public class AlertsTest extends TestBase {
    @Test
    public void checkThatTheUserCanAcceptAlert(){
        homePage.openAlertsPage(driver);
        alertsPage.interactWithSimpleAlert(driver);
        alertsPage.interactWithOkayCancelAlert(driver);
        alertsPage.interactWithOtherAlert(driver);
        alertsPage.interactWithTextAreaAlert(driver);
        alertsPage.interactWithSecureTextEntryAlert(driver);
    }
}
