package ui;

import utils.Clean;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class DemoQa {
    Clean clean = new Clean();

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com/";
        Configuration.browserSize = "1920x1080";
    }


    @Test
    public void practiceForm() {
        open("/login");
        clean.cleanBanner();

    }
}

