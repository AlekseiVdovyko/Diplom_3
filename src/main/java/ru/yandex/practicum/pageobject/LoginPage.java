package ru.yandex.practicum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.elements.ButtonElement;
import ru.yandex.practicum.elements.InputElement;

public class LoginPage {

    protected WebDriver driver;

    //локатор поля ввода email
    private String emailUserInputLocator = ".//fieldset[1]//input";

    //локатор поля ввода пароля
    private String passwordUserInputLocator = ".//fieldset[2]//input";

    //локатор кнопки Войти
    private String loginButtonLocator = ".//button[contains(@class, 'button_button') and text() = 'Войти']";


    public LoginPage() {
    }

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Set email user field")
    public void setEmailUserField(String email) {
        InputElement emailInput = new InputElement(emailUserInputLocator);
        emailInput.clearAndSetValue(email);
    }

    @Step("Set password user field")
    public void setPasswordUserField(String password) {
        InputElement passwordInput = new InputElement(passwordUserInputLocator);
        passwordInput.clearAndSetValue(password);
    }

    @Step("Check button login")
    public void loginButtonClick() {
        ButtonElement loginButton = new ButtonElement(loginButtonLocator);
        loginButton.clickButton();;
    }

    @Step("Check button login")
    public boolean isEnabledLoginButton() {
        ButtonElement loginButton = new ButtonElement(loginButtonLocator);
        return loginButton.isEnableButton();
    }

}
