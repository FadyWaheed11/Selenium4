import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

public class Chapter4WindowsManagement {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");
        System.out.println("Title: " + driver.getTitle());
    }

    @Test
    public void testNewWindowTab() {
        WebDriver newWindow = driver.switchTo().newWindow(WindowType.TAB);
        newWindow.get("http://automationpractice.com/index.php?controller=prices-drop");
        System.out.println("Title: " + driver.getTitle());
    }

    @Test
    public void testWorkingInBothWindowTabs() {
        //Automatically open & switch to the new tab
        driver.switchTo().newWindow(WindowType.TAB)
                .get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        System.out.println("Title: " + driver.getTitle());

        //Work in the window or tab
        driver.findElement(By.id("email_create")).sendKeys("fadywaheed55@gmail.com");
        driver.findElement(By.id("SubmitCreate")).click();

        //Get The window ID handles
        Set<String> allWindows = driver.getWindowHandles();
        Iterator<String> iterator = allWindows.iterator();
        String mainPage = iterator.next();

        //Switch and work in the main window or tab
        driver.switchTo().window(mainPage);
        driver.findElement(By.id("search_query_top")).sendKeys("Blue shirt");
        driver.findElement(By.name("submit_search")).click();
        System.out.println("Title: " + driver.getTitle());
    }
}
