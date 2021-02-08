package demo.pageobjects;




import static com.codeborne.selenide.Selenide.page;

public class BaseApp {


    public static Search searchPage() {
        return page(Search.class);
    }


}

