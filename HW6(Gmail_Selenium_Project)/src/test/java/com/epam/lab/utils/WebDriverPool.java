package com.epam.lab.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class WebDriverPool {

    private static ThreadLocal<WebDriver> webDriverPool = ThreadLocal.withInitial(() -> {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(new ConfigFileReader().getImplicitlyWait(), TimeUnit.SECONDS);
        return driver;
    });

    public static WebDriver getDriver() {
        return webDriverPool.get();
    }

    public static void removeDriver() {
        webDriverPool.get().quit();
        webDriverPool.remove();
    }
}