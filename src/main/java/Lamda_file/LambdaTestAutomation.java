package Lamda_file;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class LambdaTestAutomation {

     static WebDriver driver;

    @BeforeMethod
    @Parameters({"browser", "version", "platform"})
    public void setUp(String browser, String version, String platform) throws MalformedURLException {
        // Set up driver based on browser
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", browser);
        caps.setCapability("browserVersion", version);
        caps.setCapability("platformName", platform);
        caps.setCapability("network", true);
        caps.setCapability("visual", true);
        caps.setCapability("console", true);
        caps.setCapability("video", true);
        caps.setCapability("name", "LambdaTest Automation TestNG");

        // Your LambdaTest username and access key
        String username = "adityapawar180";
        String accessKey = "eZFjHaGIuMDJHoRSHwyP5iN5Z9INdXMvVBJpl2e3OLO1OunpDW";
        String gridUrl = "@hub.lambdatest.com/wd/hub";

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + gridUrl), caps);

        // Set browser size and timeouts
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


        @Test
        public static void Scenario1() throws Exception {

          //  RemoteWebDriver driver = new RemoteWebDriver(new URL("https://" + USERNAME + ":" + ACCESS_KEY + "@" + LT_GRID_URL), capabilities);
           /* System.setProperty("webdriver.chrome.driver", "Driver/chromedriver_win.exe");
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.manage().window().maximize();*/

            driver.manage().window().maximize();
            driver.get("https://www.lambdatest.com/selenium-playground");

            driver.findElement(By.xpath("//a[normalize-space()='Simple Form Demo']")).click();
            Thread.sleep(1000);
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.contains("simple-form-demo")) {
                System.out.println("URL validation passed for Scenario 1");
            } else {
                System.out.println("URL validation failed for Scenario 1");
            }

           // 4. Create a variable for a string value E.g: “Welcome to LambdaTest”.

            String message = "Welcome to LambdaTest";
            //5. Use this variable to enter values in the “Enter Message” text box.
            driver.findElement(By.xpath("//input[@id='user-message']")).sendKeys(message);

            driver.findElement(By.xpath("//button[@id='showInput']")).click();
            String displayedMessage = driver.findElement(By.xpath("//p[@id='message']")).getText();
            if (displayedMessage.equals(message)) {
                System.out.println("Message validation passed for Scenario 1");
            } else {
                System.out.println("Message validation failed for Scenario 1");
            }

        }

        //(DesiredCapabilities capabilities)
    @Test
        public static void Scenario2( ) throws Exception {
           // RemoteWebDriver driver = new RemoteWebDriver(new URL("https://" + USERNAME + ":" + ACCESS_KEY + "@" + LT_GRID_URL), capabilities);

            /*System.setProperty("webdriver.chrome.driver", "Driver/chromedriver_win.exe");
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));*/


            driver.manage().window().maximize();
            //1. Open the https://www.lambdatest.com/selenium-playground page and click “Drag & Drop Sliders” under “Progress Bars & Sliders”
            driver.get("https://www.lambdatest.com/selenium-playground");

            driver.findElement(By.linkText("Drag & Drop Sliders")).click();
            Thread.sleep(1000);



        WebElement slider = driver.findElement(By.xpath("//input[@value='15']"));
        // Simulate dragging the slider to move it
        Actions sliderAction = new Actions(driver);
        sliderAction.clickAndHold(slider).moveByOffset(212, 0).release().perform();

        // Wait for the slider to be adjusted
        Thread.sleep(1000);


        WebElement rangeValue = driver.findElement(By.xpath("//output[@id='rangeSuccess']"));
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//output[@id='rangeSuccess']")));
        int currentValue = Integer.parseInt(rangeValue.getText());
        System.out.println("current value is : "+ currentValue);
        if (currentValue == 95) {
            System.out.println("Slider validation passed for  Scenario 2");
        } else {
            System.out.println("Slider validation failed for Scenario 2");
        }


        }

        @Test
    public static void Scenario3() throws InterruptedException, AWTException {
            /*System.setProperty("webdriver.chrome.driver", "Driver/chromedriver_win.exe");
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));*/

            driver.manage().window().maximize();
        driver.get("https://www.lambdatest.com/selenium-playground");
        driver.findElement(By.linkText("Input Form Submit")).click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300);");


        WebElement SubmitBtn = driver.findElement(By.xpath("//button[normalize-space()='Submit']"));
        SubmitBtn.click();
        Thread.sleep(5000);

        String errorMessage = driver.findElement(By.xpath("//input[@id='name']")).getAttribute("validationMessage");
        System.out.println("errorMessage :"+ errorMessage);
        Assert.assertEquals("Please fill out this field.",errorMessage);
            Robot robot = new Robot();

            driver.findElement(By.name("name")).sendKeys("Aditya Pawar" + Keys.ENTER);
            driver.findElement(By.xpath("//input[@id='inputEmail4']")).sendKeys("Aditya@mailinator.com" + Keys.ENTER);
            driver.findElement(By.xpath("//input[@id='inputPassword4']")).sendKeys("Aditya@123");
            driver.findElement(By.name("company")).sendKeys("XYZ");
            driver.findElement(By.name("website")).sendKeys("www.google.com");


            WebElement countryDropdown = driver.findElement(By.name("country"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", countryDropdown);
            countryDropdown.sendKeys("United States");
            Thread.sleep(5000);

            driver.findElement(By.name("city")).sendKeys("Nagpur");

            driver.findElement(By.name("address_line1")).sendKeys("Chatrapati");

            driver.findElement(By.name("address_line2")).sendKeys("Wardha road");

            driver.findElement(By.id("inputState")).sendKeys("Maharashtra");

            driver.findElement(By.name("zip")).sendKeys("440015");

            SubmitBtn.click();

        WebElement successMessage = driver.findElement(By.xpath("//p[@class='success-msg hidden']"));
        assert successMessage.getText().equals("Thanks for contacting us, we will get back to you shortly.");

    }
}
