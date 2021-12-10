package ru.stqa.cucumber.pages;

import io.cucumber.java8.En;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CucumberTestBase implements En {

    public WebDriver driver;
    public WebDriverWait wait;

    public CucumberTestBase() {
        Before(() -> {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, 10);
        });

        After(() -> {
            driver.quit();
               driver = null;
        });
    }
}