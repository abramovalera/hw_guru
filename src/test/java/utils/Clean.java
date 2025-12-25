package utils;

import static com.codeborne.selenide.Selenide.executeJavaScript;

public class Clean {

    public void cleanBanner(){
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
    }
}
