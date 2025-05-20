package tests;

import base.TestBase;
import org.testng.annotations.Test;

public class SteppersTest extends TestBase {
    @Test
    public void checkThatStepperIsWorking() {
        homePage.openSteppersPage();
        steppersPage.increaseWithAmount(5);
        softAssert.assertEquals(steppersPage.getStepCounter(), "5");
        steppersPage.decrementStepper();
        softAssert.assertEquals(steppersPage.getStepCounter(), "4");
        softAssert.assertAll();
    }
}
