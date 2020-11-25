package com.eazyftw.websitescreenshotter.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public class VirtualBrowser extends Thread {

    protected ChromeDriver browser;

    public VirtualBrowser() {
        start();
    }

    @Override
    public void run() {
        WebDriverManager.chromedriver().setup();
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920x1080");

        this.browser = new ChromeDriver(options);
    }

    public void navigate(String url) {
        browser.get(url);
    }

    public void close() {
        browser.quit();
    }
}