package demo.pageobjects;




import static com.codeborne.selenide.Selenide.page;

public class BaseApp {


    public static LandingPage landingPage() {
        return page(LandingPage.class);
    }


}

