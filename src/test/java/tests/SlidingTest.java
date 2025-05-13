package tests;

import base.TestBase;
import org.testng.annotations.Test;

public class SlidingTest extends TestBase {
    @Test
    public void checkThatUserCanSlide() throws InterruptedException {
        homePage.openSliderPage(driver);
        slidingPage.slideRight(driver);
        softAssert.assertTrue(slidingPage.getCurrentSliderValue(driver).contains("100"));
        slidingPage.slideLeft(driver);
        System.out.println("Slider value : "+slidingPage.getCurrentSliderValue(driver));
        softAssert.assertTrue(slidingPage.getCurrentSliderValue(driver).contains("20"));
        slidingPage.slideRightForTinted(driver);
        System.out.println("Slider value : "+slidingPage.getCurrentSliderValue(driver));
        softAssert.assertAll();

    }
}
