# Website Screenshotter

With this tool, you can create screenshots of one or more websites!

## Options

`url` - the url of the website.

`fileName` - the file name. (not including .png)

`width` - the width of the image. **optional**

`height` - the height of the image. **optional**




## Example Config

**Hint:** Setting the interval to 0 makes the screenshotter only run once!

```{
  "interval": 2,
  "websites": [
    {
      "url": "https://api.corona.eazyftw.com/og_div",
      "fileName": "og.png",
      "width": 1200,
      "height": 600
    },
    {
      "url": "https://api.corona.eazyftw.com/og_div",
      "fileName": "og_full.png"
    }
  ]
}
```

