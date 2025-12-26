package utils;

import static com.codeborne.selenide.Selenide.executeJavaScript;

public class Clean {

    public static void cleanBanner(){
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
    }
}
