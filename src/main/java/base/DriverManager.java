package base;

import io.appium.java_client.ios.IOSDriver;

public class DriverManager {
    private static IOSDriver driver ;

    public static IOSDriver getDriver() {
        return driver;
    }

    public static void setDriver(IOSDriver driver) {
        DriverManager.driver = driver;
    }
}
