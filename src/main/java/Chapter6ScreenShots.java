import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class Chapter6ScreenShots {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://applitools.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void takeWebElementScreenShot() throws IOException {
        WebElement nextGenerationPlatform = driver.findElement(By.xpath("//h1[contains(text(),'Next')]"));
        File source = nextGenerationPlatform.getScreenshotAs(OutputType.FILE);
        File destination = new File("New Generation Platform.png");
        FileHandler.copy(source, destination);
    }


    @Test
    public void takeWebElementPageSectionScreenShot() throws IOException {
        WebElement pageSection = driver.findElement(By.cssSelector("#post-8>header"));
        File source = pageSection.getScreenshotAs(OutputType.FILE);
        File destination = new File("Page Section.png");
        FileUtils.copyFile(source, new File("Page Section.png"));
    }

    @Test
    public void takeFullScreenShot() throws IOException {
        File source = ((ChromeDriver) driver).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(source, new File("AppliTools Full Screenshot.png"));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
