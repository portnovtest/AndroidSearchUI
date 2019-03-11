package com.company.searchui.utils;

import com.sun.javafx.geom.Rectangle;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.Augmenter;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Image Capture and Compare Class
 *
 * @author phildolganov
 *
 */
public class ImageCapture {
    // constructor
    public ImageCapture() throws Exception {
    }

    /**
     * screenShot - method that takes ITestResult as parameter
     *
     * @param result - The result of test
     * @return String
     * @throws Exception
     */
    public static String screenShot(ITestResult result) throws Exception {
        DateFormat stamp = new SimpleDateFormat("MM.dd.yy.HH.mm.ss");
        Date date = new Date();

        ITestNGMethod method = result.getMethod();
        String testName = method.getMethodName();

        return captureScreen(testName + "_" + stamp.format(date) + ".png");
    }

    /**
     * captureScreen - method to capture the entire screen of the Browser or Mobile App
     *
     * @param filename - The filename to save it to
     * @return
     * @throws Exception
     */
    public static String captureScreen(String filename) throws Exception {
        String bitmapPath = "myPath";
        WebDriver driver = CreateDriver.getInstance().getCurrentDriver();
        File screen = null;

        if ( Global_VARS.DEF_ENVIRONMENT.equalsIgnoreCase("remote")) {
            // cast to Augmenter class for RemoteWebDriver
            screen = ((TakesScreenshot)new Augmenter().augment(driver)).getScreenshotAs(OutputType.FILE);
        } else {
            screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        }

        FileUtils.copyFile(screen, new File(bitmapPath + filename));
        return filename;
    }

    /**
     * imageSnapshot - method to take snapshot of WebElement
     *
     * @param element - The Web or Mobile Element to capture
     * @return
     * @throws Exception
     */
    public static File imageSnapshot(WebElement element) throws Exception {
        WrapsDriver wrapsDriver = (WrapsDriver) element;
        File screen = null;

        // Capture the WebElement snapshot
        screen = ((TakesScreenshot) wrapsDriver.getWrappedDriver()).getScreenshotAs(OutputType.FILE);

        // Create Buffered image instance from captured screenshot
        BufferedImage img = ImageIO.read(screen);

        // Get the width/height of the WebElement for the rectangle
        int width = element.getSize().getWidth();
        int height = element.getSize().getHeight();
        Rectangle rect = new Rectangle(width,height);

        // get the location of WebElement in a point (x,y)
        Point p = element.getLocation();

        // create image for element using location and size
        BufferedImage dest = img.getSubimage(p.getX(), p.getY(), rect.width, rect.height);

        // BMP,bmp,jpg,JPG,jpeg,wbmp,png,PNG,JPEG,WBMP,GIF,gif
        ImageIO.write(dest,"png",screen);

        return screen;
    }

    /**
     * captureImage - method to capture individual WebElement image
     *
     * @param image - the image to capture
     * @throws Exception
     */
    public static void captureImage(String image) throws Exception {
        WebDriver driver = CreateDriver.getInstance().getCurrentDriver();

        WebElement getImage = driver.findElement(By.cssSelector("img[src*='" + image + "']"));

        image = image.replace(".","_" + Global_VARS.DEF_BROWSER + ".");

        FileUtils.copyFile(imageSnapshot(getImage), new File(Global_VARS.BITMAP_PATH + image));
    }

    public enum RESULT { Matched, SizeMismatch, PixelMismatch }

    /**
     * compareImage - methopd to compare 2 images
     *
     * @param expFile - the expected file to compare
     * @param actFile - the actual file to compare
     * @return RESULT
     * @throws Exception
     */
    public static RESULT compareImage(String expFile, String actFile) throws Exception {
        RESULT compareResult = null;
        Image baseImage = Toolkit.getDefaultToolkit().getImage(expFile);
        Image actualImage = Toolkit.getDefaultToolkit().getImage(actFile);

        // get pixels of image
        PixelGrabber baseImageGrab = new PixelGrabber(baseImage,0,0,-1,-1,false);

        PixelGrabber actualImageGrab = new PixelGrabber(actualImage,0,0,-1,-1,false);

        int[] baseImageData = null;
        int[] actualImageData = null;

        // get pixels coordinates of base image
        if (baseImageGrab.grabPixels()){
            int width = baseImageGrab.getWidth();
            int height = baseImageGrab.getHeight();
            baseImageData = new int[width * height];
            baseImageData = (int[])baseImageGrab.getPixels();
        }

        // get pixels coordinates of actual image
        if (actualImageGrab.grabPixels()){
            int width = actualImageGrab.getWidth();
            int height = actualImageGrab.getHeight();
            actualImageData = new int[width * height];
            actualImageData = (int[])actualImageGrab.getPixels();
        }

        //test for size mismatch, then pixel mismatch
        if ((baseImageGrab.getHeight() != actualImageGrab.getHeight()) ||
                (baseImageGrab.getWidth() != actualImageGrab.getWidth()) ){
            compareResult = RESULT.SizeMismatch;
        } else if (java.util.Arrays.equals(baseImageData,actualImageData)){
            compareResult = RESULT.Matched;
        } else {
            compareResult = RESULT.PixelMismatch;
        }

        return compareResult;
    }
}
