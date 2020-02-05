package selenium;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class StartFirefoxDriver
{
    static FirefoxDriver driver;   
    public static FirefoxDriver startFirefox() {
        System.setProperty("webdriver.gecko.driver","C:\\SeleniumGecko\\geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }   
}
