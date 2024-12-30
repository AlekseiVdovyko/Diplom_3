package ru.yandex.practicum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.elements.ButtonElement;

public class PersonalPage {

    protected WebDriver driver;

    //локатор кнопки Выход
    private String logoutButtonLocator = ".//button[contains(@type, 'button') and text() = 'Выход']";

    //локатор ссылки на "Конструктор"
    private String constructorButtonLocator = ".//p[contains(@class, 'AppHeader_header') and text() = 'Конструктор']";

    //локатор лого Stellar Burgers
    private String logoBurgersLocator = ".//div[@class = 'AppHeader_header__logo__2D0X2']";

    public PersonalPage(WebDriver driver) {
        this.driver = driver;
    }

    public PersonalPage() {
    }

    @Step("Check button is enable")
    public boolean isEnabledLogoutButton() {
        ButtonElement logoutButton = new ButtonElement(logoutButtonLocator);
        return logoutButton.isEnableButton();
    }

    @Step("Check button constructor")
    public void constructorButtonClick() {
        ButtonElement constructorButton = new ButtonElement(constructorButtonLocator);
        constructorButton.clickButton();
    }

    @Step("Check logo burger")
    public void logoBurgersClick() {
        ButtonElement logoBurgers = new ButtonElement(logoBurgersLocator);
        logoBurgers.clickButton();
    }


    @Step("Check button login in personal account")
    public void logoutButtonClick() {
        ButtonElement logoutButton = new ButtonElement(logoutButtonLocator);
        logoutButton.clickButton();
    }
}
