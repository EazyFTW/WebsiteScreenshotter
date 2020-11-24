package com.eazyftw.websitescreenshotter.browser;

import com.eazyftw.websitescreenshotter.file.Website;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Screenshotter extends VirtualBrowser {

    private final List<Website> websites;

    public Screenshotter(List<Website> websites) {
        this.websites = websites;

        view();
    }

    public void view() {
        int success = websites.size();

        for(Website website : websites) {
            try {
                navigate(website.getUrl());

                File screenshot = ((TakesScreenshot) browser).getScreenshotAs(OutputType.FILE);
                BufferedImage fullImg;

                fullImg = ImageIO.read(screenshot);

                if (website.notFullPage())
                    fullImg = fullImg.getSubimage(0, 0, website.getWidth(), website.getHeight());

                ImageIO.write(fullImg, "png", screenshot);

                File screenshotLocation = new File(website.getFileName());
                FileUtils.copyFile(screenshot, screenshotLocation);
            } catch (RasterFormatException | IOException e) {
                success--;
                System.out.println("ERROR - Failed to take a screenshot of '" + website.getUrl() + "' with message: \"" + e.getMessage() + "\", skipping url....");
                //e.printStackTrace();
            }
        }

        if(success > 0)
            System.out.println("EXECUTED - Successfully took a screenshot of " + success + "/" + websites.size() + " websites!");

        close();
    }
}
