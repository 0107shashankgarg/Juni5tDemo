package demo.pageobjects.basic.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public abstract class AppMainPage extends BasePage {

    SelenideElement leftPanel = $x("//div[@class='panel-body']");
    SelenideElement leftPanelOptions = leftPanel.$x("/ul/li");
    SelenideElement allExamples = leftPanelOptions.$x("/a");

}
