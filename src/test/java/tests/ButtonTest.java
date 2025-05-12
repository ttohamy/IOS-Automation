package tests;

import base.TestBase;
import org.testng.annotations.Test;

public class ButtonTest extends TestBase {
    @Test
    public void test(){
        System.out.println("testing .....");
        homePage.openActivityIndicator(TestBase.driver);
    }

}
