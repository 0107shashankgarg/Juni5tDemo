package demo.ui.conditions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Driver;
import org.openqa.selenium.WebElement;

public class AttributesConditions {
    public static Condition attributeContainsValue(final String attributeName, final String expectedAttributeValue) {
        return new Condition("attributeContainsValue") {

            @Override
            public boolean apply(Driver driver, WebElement webElement) {
                return getAttributeValue(webElement, attributeName).contains(expectedAttributeValue);
            }

            @Override
            public String toString() {
                return attributeName + '=' + expectedAttributeValue;
            }
        };
    }

    private static String getAttributeValue(WebElement element, String attributeName) {
        String attr = element.getAttribute(attributeName);
        return attr == null ? "" : attr;
    }
}
