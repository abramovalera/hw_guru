package ui;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import utils.RandomData;
import utils.RandomUtils;

public class TestBase {
    protected RandomUtils randomUtils = new RandomUtils();
    protected RandomData randomData = new RandomData();
    protected TestData testData;

    @BeforeEach
    public void setupTest() {
        testData = new TestData(randomData, randomUtils);
    }

    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
    }
}
