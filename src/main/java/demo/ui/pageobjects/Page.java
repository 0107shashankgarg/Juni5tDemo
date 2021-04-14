package demo.ui.pageobjects;


import static com.codeborne.selenide.Selenide.page;

public class Page {


    public static LandingPage landingPage() {
        return page(LandingPage.class);
    }


    public void LaenterUserName(String Username) {

    }

}
