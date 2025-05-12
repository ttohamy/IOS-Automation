package tests;

import base.TestBase;
import org.testng.annotations.Test;

public class SlidingTest extends TestBase {
    @Test
    public void checkThatUserCanSlide() throws InterruptedException {
        homePage.openSliderPage(driver);
        slidingPage.slideRight(driver);
        slidingPage.slideLeft(driver);
    }
}
