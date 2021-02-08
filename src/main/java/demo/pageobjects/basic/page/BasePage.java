package demo.pageobjects.basic.page;

import demo.config.ConfigMapping;
import com.codeborne.selenide.WebDriverRunner;
import org.aeonbits.owner.ConfigCache;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.executeJavaScript;

public abstract class BasePage {

    protected ConfigMapping cfg = ConfigCache.getOrCreate(ConfigMapping.class, System.getProperties());
    private static final Integer TIME_OUT_IN_SECONDS = 30;

    protected static boolean waitForJStoLoad() {

        WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), TIME_OUT_IN_SECONDS);

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            try {
                return ((Long) executeJavaScript("return jQuery.active") == 0);
            } catch (Exception e) {
                return true;
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = driver -> executeJavaScript("return document.readyState")
                .toString().equals("complete");

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }
}
