package tests;

import base.TestBase;
import org.testng.annotations.Test;

public class DatePickerTest extends TestBase {
    @Test
    public void userCanSelectSpecificDate() throws InterruptedException {
        homePage.openDatePickerPage();
        datePickerPage.selectSpecificDate();
        datePickerPage.selectSpecificTime();
    }
}
