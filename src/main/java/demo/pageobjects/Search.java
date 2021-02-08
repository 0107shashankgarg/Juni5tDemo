package demo.pageobjects;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import demo.pageobjects.basic.page.BasePage;

import static com.codeborne.selenide.Selenide.$x;

public class Search extends BasePage {

    private SelenideElement searchBox = $x("//*[@name='q']");


    public Search open() {
        Selenide.open(cfg.appBaseUrl());
        return this;
    }


    public void enterSearchTerm() {
        searchBox.sendKeys("Junit5");
    }
    }

