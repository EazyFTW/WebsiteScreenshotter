package com.eazyftw.websitescreenshotter;

import com.eazyftw.websitescreenshotter.browser.Screenshotter;
import com.eazyftw.websitescreenshotter.file.Config;

import java.util.concurrent.TimeUnit;

public class WebsiteScreenshotter {

    public static void main(String[] args) {
        if(!Config.getInstance().containsWebsite()) {
            System.out.println("ERROR - No website found, shutting down....");
            System.exit(0);

            return;
        }

        int websitesAmount = Config.getInstance().getWebsites().size();
        int interval = Config.getInstance().getInterval();

        System.out.println("SUCCESS - Found " + websitesAmount + " website" + (websitesAmount == 1 ? "" : "s") + ".");
        if(interval <= 0) {
            System.out.println("SUCCESS - Now starting!");
        } else {
            System.out.println("SUCCESS - Now starting with an interval of " + interval + " minute" + (interval == 1 ? "" : "s") + "!");
        }

        start();
    }

    public static void start() {
        if(Config.getInstance().getInterval() <= 0) {
            new Screenshotter(Config.getInstance().getWebsites());
        } else {
            new Thread(() -> {
                while(true) {
                    new Screenshotter(Config.getInstance().getWebsites());

                    try {
                        Thread.sleep(TimeUnit.MINUTES.toMillis(Config.getInstance().getInterval()));
                    } catch (InterruptedException e) {
                        System.out.println("ERROR - Thread was interrupted.");
                        //e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
