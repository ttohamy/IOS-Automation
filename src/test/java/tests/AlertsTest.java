package tests;

import base.TestBase;
import org.testng.annotations.Test;

public class AlertsTest extends TestBase {
    @Test
    public void checkThatTheUserCanAcceptAlert(){
        homePage.openAlertsPage();
        alertsPage.interactWithSimpleAlert();
        alertsPage.interactWithOkayCancelAlert();
        alertsPage.interactWithOtherAlert();
        alertsPage.interactWithTextAreaAlert();
        alertsPage.interactWithSecureTextEntryAlert();
    }
}
