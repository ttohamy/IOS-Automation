package tests;

import base.TestBase;
import org.testng.annotations.Test;

public class StakeViewTest extends TestBase {
    @Test
    public void checkThatNewStackViewIsAdded(){
        homePage.openStakeViewsPage();
        stackViewPage.clickAddButton();
        softAssert.assertTrue(stackViewPage.isSecondTextFieldAppears());
        stackViewPage.clickSecondAddButton();
        stackViewPage.clickSecondAddButton();
        stackViewPage.clickDecrementButton();
        softAssert.assertTrue(stackViewPage.isColorBoxDisappears());
        softAssert.assertAll();
    }
}
