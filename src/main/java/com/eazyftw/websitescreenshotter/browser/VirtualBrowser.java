package com.eazyftw.websitescreenshotter.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

public class VirtualBrowser {

    protected ChromeDriver browser;

    public VirtualBrowser() {
        WebDriverManager.chromedriver().setup();
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");

        this.browser = new ChromeDriver();
    }

    public void navigate(String url) {
        this.browser.manage().window().setSize(new Dimension(1920, 1080));
        this.browser.manage().window().setPosition(new Point(0, 0));
        browser.get(url);
    }

    public void close() {
        browser.close();
    }
}