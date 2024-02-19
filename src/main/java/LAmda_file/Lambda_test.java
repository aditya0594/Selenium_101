package LAmda_file;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;



public class Lambda_test {
    static WebDriver driver;

    @BeforeMethod
    public void Setup(){

        System.setProperty("webdriver.chrome.driver", "Driver/chromedriver_win.exe");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();


    }

    @Test(priority = 1,enabled = true)
    public static void test_method() {
        SoftAssert softAssert = new SoftAssert();

        // Navigate to LambdaTest website
        driver.get("https://www.lambdatest.com/");

        // explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("body")));

        // Scroll'SEE ALL INTEGRATIONS'
        WebElement seeAllIntegrations = driver.findElement(By.linkText("SEE ALL INTEGRATIONS"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", seeAllIntegrations);

        // Click on 'SEE ALL INTEGRATIONS' and open in a new tab
        seeAllIntegrations.sendKeys(Keys.chord(Keys.CONTROL, Keys.RETURN));

        // Save window handles in a List
        Set<String> windowHandles = driver.getWindowHandles();
        List<String> handlesList = new ArrayList<>(windowHandles);

        // Print window handles of opened windows
        System.out.println("Window Handles of opened windows: " + handlesList);

        // Switch to the new tab
        driver.switchTo().window(handlesList.get(1));

        // Verify URL
        String expectedUrl = "https://www.lambdatest.com/integrations";
        if (!driver.getCurrentUrl().equals(expectedUrl)) {
            throw new AssertionError("URL mismatch!");
        }

        // Scroll to WebElement 'Codeless Automation'
        WebElement codelessAutomation = driver.findElement(By.xpath("//a[normalize-space()='Codeless Automation']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", codelessAutomation);

        // Click on 'LEARN MORE' link for Testing Whiz
        WebElement learnMoreLink = driver.findElement(By.xpath("//a[normalize-space()='Integrate Testing Whiz with LambdaTest']"));
        learnMoreLink.click();

        // Check if title of the page is as expected
        String expectedTitle = "TestingWhiz Integration | LambdaTest";
        if (!driver.getTitle().equals(expectedTitle)) {
            //throw new AssertionError("Title mismatch!");
            softAssert.fail("Title mismatch!");
        }

        // Close the current window
        driver.close();

        // Print current window count
        System.out.println("Current window count: " + driver.getWindowHandles().size());

        // Switch back to the first tab
        driver.switchTo().window(handlesList.get(0));

        // Set URL to https://www.lambdatest.com/blog
        driver.get("https://www.lambdatest.com/blog");

        // Click on 'Community' link
       driver.findElement(By.xpath("//a[@href='https://community.lambdatest.com/'][normalize-space()='Community']")).click();

        // Verify URL
        String expectedCommunityUrl = "https://community.lambdatest.com/";
        if (!driver.getCurrentUrl().equals(expectedCommunityUrl)) {
            throw new AssertionError("Community URL mismatch!");
        }

        // Close the current browser window
        driver.quit();
    }
}

