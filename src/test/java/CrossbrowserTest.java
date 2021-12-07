import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@RunWith(value = Parameterized.class)
public class CrossbrowserTest {

    private static final String key = System.getenv("ACCESS_KEY");
    private static final String name = System.getenv("NAME");
    private String browserName;
    private String browserVersion;
    private String platform;
    public static WebDriver driver;

    public CrossbrowserTest(String browserName, String browserVersion, String platform) {
        this.browserName = browserName;
        this.browserVersion = browserVersion;
        this.platform = platform;
    }


    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabillities = new DesiredCapabilities();

        capabillities.setCapability(CapabilityType.BROWSER_NAME, browserName);
        capabillities.setCapability(CapabilityType.VERSION, browserVersion);
        capabillities.setCapability(CapabilityType.PLATFORM, platform);

//        MutableCapabilities sauceOptions = new MutableCapabilities();
//        sauceOptions.setCapability("username", name);
//        sauceOptions.setCapability("accesskey", key);
//        List<String> tags = Arrays.asList("sauceDemo", "demoTest", "module4", "javaTest");
//        sauceOptions.setCapability("tags", tags);
//        capabillities.setCapability("sauce:options", sauceOptions);


       var url = new URL("https://" + name + ":" + key+ "@ondemand.eu-central-1.saucelabs.com:443/wd/hub");
        driver = new RemoteWebDriver(url, capabillities);

    }


    @Parameterized.Parameters
    public static Object[] getColorData() {
        return new Object[][]{
                {"firefox", "latest", "Windows 10"},
                {"internet explorer", "11.0", "Windows 8.1"},
                {"safari", "12", "macOS 10.13"},
                {"chrome", "75", "Windows 10"},
                {"firefox", "latest-1", "Windows 10"}
        };
    }


    @Test
    public void firstTest() {
        driver.get("http://google.com.ua");
        System.out.println(driver.getTitle() + " " + browserName);
    }


    @After
    public void cleanUpAfterTestMethod() {
        ((JavascriptExecutor) driver).executeScript("sauce:job-result=" + ("passed"));
        driver.quit();
    }
}
