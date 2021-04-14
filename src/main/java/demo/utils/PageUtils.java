package demo.utils;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import demo.config.ConfigMapping;
import demo.pageobjects.basic.page.BasePage;
import org.aeonbits.owner.ConfigCache;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class PageUtils extends BasePage {


    private static ConfigMapping cfg = ConfigCache.getOrCreate(ConfigMapping.class, System.getProperties());

    public static void clearAndType(SelenideElement inputElement, String data) {
        inputElement.clear();
        inputElement.sendKeys(data);
    }

    public static List<String> convertElementsCollectionToStringList(ElementsCollection elementsCollection, boolean removeTrailingComma) {
        List<String> result = new ArrayList<>();
        for (SelenideElement element : elementsCollection) {
            String textToAdd = element.getText().trim();
            if (removeTrailingComma && textToAdd.endsWith(",")) {
                textToAdd = textToAdd.substring(0, textToAdd.length() - 1);
            }
            result.add(textToAdd);
        }
        return result;
    }

    public static String bindXPaths(String firstXPath, String secondXPath, String... additionalXPaths) {
        String resultXPath = firstXPath + " | " + secondXPath;
        for (String xPath : additionalXPaths) {
            resultXPath += " | " + xPath;
        }
        return resultXPath;
    }

    public static boolean checkJsErrors(Logger log) {
        List<LogEntry> listErrors = getWebDriver().manage().logs().get(LogType.BROWSER).getAll();

        if (cfg.isSuppressKnownIssues())
            listErrors = getErrorListAfterIgnoringResourceError(listErrors);
        boolean areErrorsPresent = !listErrors.isEmpty();
        if (areErrorsPresent) {
            log.error("Start output JSErrors");
            for (LogEntry logEntry : listErrors) {
                log.error(logEntry);
            }
            log.error("Finish output JSErrors");
        }
        return areErrorsPresent;
    }

    public static List getErrorListAfterIgnoringResourceError(List<LogEntry> listErrors) {
        List<LogEntry> listOfErrors = new ArrayList(listErrors);

        for (LogEntry Error : listOfErrors) {
            if (Error.toString()
                    .contains("Failed to load resource: the server responded with a status of 404 (Not Found)")
                    || (Error.toString().contains("ERROR [route-href]"))) {
                listErrors.remove(Error);
            }
        }
        return listErrors;

    }
}
