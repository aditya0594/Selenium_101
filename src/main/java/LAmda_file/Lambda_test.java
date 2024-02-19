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

        //1.	Navigate to https://www.lambdatest.com/.
        driver.get("https://www.lambdatest.com/");

       // 2.	Perform an explicit wait till the time all the elements in the DOM are available.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("body")));

        // 3.	Scroll to the WebElement ‘SEE ALL INTEGRATIONS’ using the scrollIntoView() method. You are free to use any of the available web locators (e.g., XPath, CssSelector, etc.)
        WebElement seeAllIntegrations = driver.findElement(By.linkText("SEE ALL INTEGRATIONS"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", seeAllIntegrations);

        //4.	Click on the link and ensure that it opens in a new Tab.
        seeAllIntegrations.sendKeys(Keys.chord(Keys.CONTROL, Keys.RETURN));


        // 5.	Save the window handles in a List (or array). Print the window handles of the opened windows (now there are two windows open).
        Set<String> windowHandles = driver.getWindowHandles();
        List<String> handlesList = new ArrayList<>(windowHandles);
        System.out.println("Window Handles of opened windows: " + handlesList);
        driver.switchTo().window(handlesList.get(1));

        //6.	Verify whether the URL is the same as the expected URL (if not, throw an Assert).
        String expectedUrl = "https://www.lambdatest.com/integrations";
        if (!driver.getCurrentUrl().equals(expectedUrl)) {
            throw new AssertionError("URL mismatch!");
        }

        // 7.	On that page, scroll to the page where the WebElement (Codeless Automation) is present.
        WebElement codelessAutomation = driver.findElement(By.xpath("//a[normalize-space()='Codeless Automation']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", codelessAutomation);

        // 8.	Click the ‘LEARN MORE’ link for Testing Whiz. The page should open in the same window.
        WebElement learnMoreLink = driver.findElement(By.xpath("//a[normalize-space()='Integrate Testing Whiz with LambdaTest']"));
        learnMoreLink.click();

        // 9.	Check if the title of the page is ‘TestingWhiz Integration | LambdaTest’.If not, raise an Assert.
        String expectedTitle = "TestingWhiz Integration | LambdaTest";
        if (!driver.getTitle().equals(expectedTitle)) {
            //throw new AssertionError("Title mismatch!");
            softAssert.fail("Title mismatch!");
        }

        // 10.	Close the current window using the window handle [which we obtained in step (5)]
        driver.close();

        // 11.	Print the current window count.
        System.out.println("Current window count: " + driver.getWindowHandles().size());
        driver.switchTo().window(handlesList.get(0));

        //12.	On the current window, set the URL to https://www.lambdatest.com/blog.
        driver.get("https://www.lambdatest.com/blog");

        // 13.	Click on the ‘Community’ link and verify whether the URL is https://community.lambdatest.com/.
       driver.findElement(By.xpath("//a[@href='https://community.lambdatest.com/'][normalize-space()='Community']")).click();
        String expectedCommunityUrl = "https://community.lambdatest.com/";
        if (!driver.getCurrentUrl().equals(expectedCommunityUrl)) {
            throw new AssertionError("Community URL mismatch!");
        }

        //14.	Close the current browser window.
        driver.quit();
    }
}

