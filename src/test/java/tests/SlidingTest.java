package tests;

import base.TestBase;
import org.testng.annotations.Test;

public class SlidingTest extends TestBase {
    @Test
    public void checkThatUserCanSlide() throws InterruptedException {
        homePage.openSliderPage();
        slidingPage.slideRight();
        softAssert.assertTrue(slidingPage.getCurrentSliderValue().contains("100"));
        slidingPage.slideLeft();
        System.out.println("Slider value : "+slidingPage.getCurrentSliderValue());
        softAssert.assertTrue(slidingPage.getCurrentSliderValue().contains("20"));
        slidingPage.slideRightForTinted();
        System.out.println("Slider value : "+slidingPage.getCurrentSliderValue());
        softAssert.assertAll();

    }
}
