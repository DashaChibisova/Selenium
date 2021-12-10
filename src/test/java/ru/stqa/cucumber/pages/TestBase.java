package ru.stqa.cucumber.pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

public class TestBase {

    public WebDriver driver;
    public WebDriverWait wait;


    @Before
    public void setUp()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

//    @After
//    public void tearDown()
//    {
//        driver.quit();
//        driver = null;
//    }

}
