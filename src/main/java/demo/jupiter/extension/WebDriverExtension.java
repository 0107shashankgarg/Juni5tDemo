package demo.jupiter.extension;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import demo.config.ConfigMapping;
import org.aeonbits.owner.ConfigCache;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import static com.codeborne.selenide.Screenshots.takeScreenShot;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class WebDriverExtension implements BeforeEachCallback, AfterEachCallback, TestExecutionExceptionHandler {

    private static final org.apache.logging.log4j.Logger LOG = LogManager.getLogger(WebDriverExtension.class);

    private static final String RESOLUTION_FULL_HD = "1920x1080x24";
    private static final Dimension DIMENSION_FULL_HD = new Dimension(1920, 1080);
    private ConfigMapping cfg = ConfigCache.getOrCreate(ConfigMapping.class, System.getProperties());

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        Optional<Method> testMethod = extensionContext.getTestMethod();
        initDriver(cfg.selenideBrowser(),
                testMethod.isPresent() ? testMethod.get().getName() : null);
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        getWebDriver().quit();
    }

    @Override
    public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) throws Throwable {
        sendScreenshotOnFailure(throwable);
        throw throwable;
    }

    public void initDriver(String browser, String testName) {
        Configuration.screenshots = false;
        if (cfg.isRemote()) {
            RemoteWebDriver webDriver = initRemoteDriver(browser, testName, true);
            WebDriverRunner.setWebDriver(webDriver);
        }
        //Todo implelemt a more concrete way to instanciate the webdriver
        else
            Selenide.open("https://www.google.co.in/");
    }

    private RemoteWebDriver initRemoteDriver(String browser, String testName, boolean retryIfError) {
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setBrowserName(browser);
        capability.setCapability("name", testName);
        capability.setCapability("screenResolution", RESOLUTION_FULL_HD);
        capability.setCapability("enableVideo", true);
        try {
            RemoteWebDriver driver = new RemoteWebDriver(new URL(cfg.remoteDriverUrl()), capability);
            driver.setFileDetector(new LocalFileDetector());
            driver.manage().window().setSize(DIMENSION_FULL_HD);
            if (isAlive(driver)) {
                return driver;
            } else {
                if (retryIfError) {
                    LOG.warn("Session not found, try to re-init");
                    return initRemoteDriver(browser, testName, false);
                } else {
                    throw new IllegalStateException("Remote session is die");
                }
            }
        } catch (MalformedURLException e) {
            throw new IllegalStateException("Error while configuring url for remote web driver");
        }
    }

    private boolean isAlive(WebDriver driver) {
        try {
            driver.getCurrentUrl();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private void sendScreenshotOnFailure(Throwable exception) {
        String screenshotFileName = Long.toString(System.currentTimeMillis());
        LOG.error("RP_MESSAGE#FILE#{}#{}", new File(takeScreenShot(screenshotFileName)), exception.getMessage());
    }
}