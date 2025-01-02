package ru.yandex.practicum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.elements.LinkElement;

public class RecoveryPage {

    protected WebDriver webDriver;

    //локатор ссылки входа в личный кабинет
    private String loginAccountLinkLocator = ".//a[contains(@class, 'Auth_link') and text() = 'Войти']";

    public RecoveryPage() {
    }

    public RecoveryPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Check login user via recovery form")
    public void loginAccountLinkClick() {
        LinkElement loginAccountLink = new LinkElement(loginAccountLinkLocator);
        loginAccountLink.clickLink();
    }
}
