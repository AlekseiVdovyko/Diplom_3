package ru.yandex.practicum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.elements.ButtonElement;
import ru.yandex.practicum.elements.InputElement;
import ru.yandex.practicum.elements.LinkElement;

public class RegisterPage {
    protected WebDriver driver;

    //локатор поля ввода имени
    private String nameUserInputLocator = ".//fieldset[1]//input";

    //локатор поля ввода email
    private String emailUserInputLocator = ".//fieldset[2]//input";

    //локатор поля ввода пароля
    private String passwordUserInputLocator = ".//fieldset[3]//input";

    //локатор кнопки регистрации
    private String registerButtonLocator = ".//button[contains(@class, 'button_button') and text() = 'Зарегистрироваться']";

    //локатор наличия текста неверного пароля менее 6 символов при регистрации
    private String incorrectPasswordTextLocator = ".//p[text() = 'Некорректный пароль']";

    //локатор ссылки входа в личный кабинет
    private String loginAccountLinkLocator = ".//a[contains(@class, 'Auth_link') and text() = 'Войти']";

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public RegisterPage() {
    }

    @Step("Check set name user to field")
    public void setNameUserField(String name) {
        InputElement nameUserInput = new InputElement(nameUserInputLocator);
        nameUserInput.clearAndSetValue(name);
    }

    @Step("Check set email user to field")
    public void setEmailUserField(String email) {
        InputElement emailUserInput = new InputElement(emailUserInputLocator);
        emailUserInput.clearAndSetValue(email);
    }

    @Step("Check set password user to field")
    public void setPasswordUserField(String password) {
        InputElement passwordUserInput = new InputElement(passwordUserInputLocator);
        passwordUserInput.clearAndSetValue(password);
    }

    @Step("Check set incorrect password user to field")
    public boolean isIncorrectPasswordText() {
        InputElement incorrectPasswordText = new InputElement(incorrectPasswordTextLocator);
        return incorrectPasswordText.isDisplayText();
    }

    @Step("Check button confirm registration")
    public void confirmRegisterButtonClick() {
        ButtonElement registerButton = new ButtonElement(registerButtonLocator);
        registerButton.clickButton();
    }

    @Step("Check login via link")
    public void loginAccountLinkClick() {
        LinkElement loginAccount = new LinkElement(loginAccountLinkLocator);
        loginAccount.clickLink();
    }
}
