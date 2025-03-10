package contact_app_tests;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.RetryAnalyzer;

public class AddContactTest extends TestBase {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void checkThatUserCanAddNewContact(){
        contactAppHomePage.openAddContactPage(driver);
        addContactPage.addContactInfo(driver,"Mohamed" , "Eltohamy");
        logger.info("assert that share contact button appears");
        Assert.assertTrue(addContactPage.isShareContactButtonAppears(driver));
    }
}
