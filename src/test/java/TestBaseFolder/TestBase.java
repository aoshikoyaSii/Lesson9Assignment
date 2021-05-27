package TestBaseFolder;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class TestBase {
    private WebDriver driver;
    static final Logger logger = LoggerFactory.getLogger(TestBaseFolder.TestBase.class);


    public WebDriver getDriver(){
        return driver;
    }

    @BeforeEach
    void setup(){
        ChromeOptions options = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger.info(">>>>> Implicit wait  set to 10 seconds <<<<<< ");
    }
    @AfterEach
    void teardown(){
        driver.quit();
        logger.debug(">>>> driver  quit <<<<<");
        logger.info(">>>> Finish Test <<<<<");
    }
}
