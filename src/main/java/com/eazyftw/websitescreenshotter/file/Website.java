package com.eazyftw.websitescreenshotter.file;

public class Website {

    private final String url;
    private final String fileName;

    private final int height;
    private final int width;

    public Website(String url, String fileName, int height, int width) {
        this.url = url;
        this.fileName = fileName;
        this.height = height;
        this.width = width;
    }

    public String getUrl() {
        return url;
    }

    public String getFileName() {
        return fileName;
    }

    public int getHeight() {
        return Math.min(height, 1080);
    }

    public int getWidth() {
        return Math.min(width, 1920);
    }

    public boolean notFullPage() {
        return this.height != 0 || this.width != 0;
    }
}
