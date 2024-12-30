package ru.yandex.practicum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.elements.ButtonElement;

public class MainPage {

    protected WebDriver driver;

    //локатор кнопки "Войти в аккаунт" на стартовой странице
    private String enterAccountButtonLocator = ".//button[contains(@class, 'button_button') and text() = 'Войти в аккаунт']";

    //локатор кнопки "Личный Кабинет"
    private String personalAccountButtonLocator = ".//p[contains(@class, 'AppHeader_header') and text() = 'Личный Кабинет']";

    //локатор кнопки "Оформить заказ"
    private String placeOrderButtonLocator = ".//button[contains(@class, 'button_button') and text() = 'Оформить заказ']";

    //локатор раздела Булки
    private String bunsSectionLocator = ".//span[text() = 'Булки']";

    //локатор раздела Булки при переходе
    private String currentBunsSectionLocator = ".//div[contains(@class, 'type_current')]/span[text() = 'Булки']";

    //локатор раздела Соусы
    private String saucesSectionLocator = ".//span[text() = 'Соусы']";

    //локатор раздела Соусы при переходе
    private String currentSaucesSectionLocator = ".//div[contains(@class, 'type_current')]/span[text() = 'Соусы']";

    //локатор раздела Начинки
    private String fillingsSectionLocator = ".//span[text() = 'Начинки']";

    //локатор раздела Начинки при переходе
    private String currentFillingsSectionLocator = ".//div[contains(@class, 'type_current')]/span[text() = 'Начинки']";

    public MainPage() {
    }

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Check button login account on main page")
    public void enterAccountButtonClick() {
        ButtonElement enterLoginButton = new ButtonElement(enterAccountButtonLocator);
        enterLoginButton.clickButton();
    }

    @Step("Check button is enable")
    public boolean isEnabledPlaceOrderButton() {
        ButtonElement placeOrderButton = new ButtonElement(placeOrderButtonLocator);
        return placeOrderButton.isEnableButton();
    }

    @Step("Check button personal account on main page")
    public void personalAccountButtonClick() {
        ButtonElement personalAccountButton = new ButtonElement(personalAccountButtonLocator);
        personalAccountButton.clickButton();
    }

    @Step("Check switch buns section")
    public boolean bunsSectionLocatorClick() {
        ButtonElement bunsSection = new ButtonElement(bunsSectionLocator);
        bunsSection.clickButton();
        ButtonElement currentBunsSection = new ButtonElement(currentBunsSectionLocator);
        return currentBunsSection.isEnableButton();
    }

    @Step("Check switch sauces section")
    public boolean saucesSectionLocatorClick() {
        ButtonElement saucesSection = new ButtonElement(saucesSectionLocator);
        saucesSection.clickButton();
        ButtonElement currentSaucesSection = new ButtonElement(currentSaucesSectionLocator);
        return currentSaucesSection.isEnableButton();
    }

    @Step("Check switch filling section")
    public boolean fillingsSectionLocatorClick() {
        ButtonElement fillingsSection = new ButtonElement(fillingsSectionLocator);
        fillingsSection.clickButton();
        ButtonElement currentFillingsSection = new ButtonElement(currentFillingsSectionLocator);
        return currentFillingsSection.isEnableButton();
    }
}
