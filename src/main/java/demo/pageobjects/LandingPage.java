package demo.pageobjects;


import com.codeborne.selenide.Selenide;
import demo.pageobjects.basic.page.BasePage;

public class LandingPage extends BasePage {


    //div[@class='panel-body']

    public LandingPage open() {
        Selenide.open(cfg.appBaseUrl());
        return this;
    }


}

