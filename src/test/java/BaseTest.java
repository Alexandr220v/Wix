import config.PropertyUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    private final Properties config = PropertyUtils.loadProperties("config.properties");
    protected static final Logger LOGGER = Logger.getLogger(BaseTest.class);
    private static final long PAGE_LOAD_TIMEOUT = 10;
    private static final long TIMEOUT = 10;
    protected WebDriver driver;
    public final String BASE_URL = "https://kolesniknikolai92.wixsite.com/" + "automation-ait";
    @BeforeMethod(alwaysRun = true)
    public void setDriver() throws Exception {
        try {
            System.setProperty("webdriver.chrome.driver", config.getProperty("chromedriver"));
            LOGGER.info("Initializing Chrome driver...");
            driver = new ChromeDriver();
            driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.get(BASE_URL);
        } catch (Exception e) {
            LOGGER.info("Error initializing chrome driver");
            throw e;
        }
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        LOGGER.info("Closing Chrome driver...");
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
