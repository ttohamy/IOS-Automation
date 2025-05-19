package base;

import com.beust.ah.A;
import contacts_app_pages.AddContactPage;
import contacts_app_pages.ContactAppHomePage;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import pages.AlertsPage;
import pages.DatePickerPage;
import pages.HomePage;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.PublicKey;
import java.sql.Driver;

import org.apache.logging.log4j.LogManager;
import pages.SlidingPage;

public class TestBase {
    protected static IOSDriver driver ;
    protected SoftAssert softAssert ;
    protected Logger logger = LogManager.getLogger(this);
    protected HomePage homePage ;
    protected ContactAppHomePage contactAppHomePage ;
    protected AddContactPage addContactPage;
    protected SlidingPage slidingPage;
    protected AlertsPage alertsPage;
    protected DatePickerPage  datePickerPage;
//Simulator Capabilities
    @BeforeTest
    public void intiDriver() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        //udid : 00008030-0006594C2668202E
        cap.setCapability("appium:app", System.getProperty("user.dir") + "/app/UIKitCatalog.app");
//        cap.setCapability("appium:bundleId", "com.apple.MobileAddressBook");
        cap.setCapability("appium:automationName", "XCUITest");
        cap.setCapability("appium:platformName", "iOS");
        cap.setCapability("appium:platformVersion", "18.2");
        cap.setCapability("appium:deviceName", "iPhone 16 Pro");
//        cap.setCapability("appium:autoGrantPermissions", true);
//        cap.setCapability("appium:noReset", false);
//        cap.setCapability("appium:fullReset", false);
        try {
            driver = new IOSDriver(new URL("http://127.0.0.1:4723"), cap);
            DriverManager.setDriver(driver);
            System.out.println("Appium driver session created successfully.");
        } catch (Exception e) {
            System.err.println("Error creating Appium driver session: " + e.getMessage());
            throw e;
        }
    }
//Real Device Capabilities
//@BeforeTest
//public void initiateDriver() throws MalformedURLException {
//    DesiredCapabilities capabilities = new DesiredCapabilities();
////    // Set the capabilities
//    capabilities.setCapability("appium:app", "/Users/mohamedeltohamy/Library/Developer/Xcode/DerivedData/UIKitCatalog-hkqkrtujzjovofcrfhyjstvdbskw/Build/Products/Debug-iphoneos/UIKitCatalog.app");
//        capabilities.setCapability("appium:automationName", "XCUITest");
//        capabilities.setCapability("appium:platformName", "iOS");
//        capabilities.setCapability("appium:udid", "00008030-0006594C2668202E");
//        capabilities.setCapability("appium:platformVersion", "18.2");
//        capabilities.setCapability("appium:deviceName", "iPhone 11");
//        capabilities.setCapability("appium:xcodeOrgId", "mohamed.eltohamy.ahmed@gmail.com");
//        capabilities.setCapability("appium:xcodeSigningId", "iPhone Developer");
//    try {
//            driver = new IOSDriver(new URL("http://127.0.0.1:4723"), capabilities);
//            System.out.println("Appium driver session created successfully.");
//        } catch (Exception e) {
//            System.err.println("Error creating Appium driver session: " + e.getMessage());
//            throw e;
//        }
//}
    @BeforeTest
    public void initiatePages(){
        homePage = new HomePage(driver);
        contactAppHomePage = new ContactAppHomePage(driver);
        addContactPage = new AddContactPage(driver);
        slidingPage = new SlidingPage(driver);
        alertsPage = new AlertsPage(driver);
        datePickerPage = new DatePickerPage(driver);
    }
    @BeforeMethod
    public void beforeMethod(){
        softAssert = new SoftAssert();
    }
}
