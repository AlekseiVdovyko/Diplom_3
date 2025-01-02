import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import ru.yandex.practicum.pageobject.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.yandex.practicum.pageobject.Constants.MAIN_PAGE_URL;

public class ConstructorBurgerTest {

    @Test
    @DisplayName("Check switch bun section")
    @Description("Can switch bun section")
    public void checkBunsSectionTest() {
        open(MAIN_PAGE_URL, MainPage.class);
        MainPage mainPage = new MainPage();
        mainPage.saucesSectionLocatorClick();
        boolean actual = mainPage.bunsSectionLocatorClick();
        Assert.assertTrue("Переход в раздел \"Булки\" не выполнен", actual);
    }

    @Test
    @DisplayName("Check switch sauces section")
    @Description("Can switch sauces section")
    public void checkSaucesSectionTest() {
        open(MAIN_PAGE_URL, MainPage.class);
        MainPage mainPage = new MainPage();
        boolean actual = mainPage.saucesSectionLocatorClick();
        Assert.assertTrue("Переход в раздел \"Соусы\" не выполнен", actual);
    }

    @Test
    @DisplayName("Check switch fillings section")
    @Description("Can switch fillings section")
    public void checkFillingsSectionTest() {
        open(MAIN_PAGE_URL, MainPage.class);
        MainPage mainPage = new MainPage();
        boolean actual = mainPage.fillingsSectionLocatorClick();
        Assert.assertTrue("Переход в раздел \"Начинки\" не выполнен", actual);
    }
}
