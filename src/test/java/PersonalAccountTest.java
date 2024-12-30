import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.api.UserApi;
import ru.yandex.practicum.models.UserData;
import ru.yandex.practicum.pageobject.LoginPage;
import ru.yandex.practicum.pageobject.MainPage;
import ru.yandex.practicum.pageobject.PersonalPage;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.core.Is.is;
import static ru.yandex.practicum.models.UserGenerator.getRandomUser;
import static ru.yandex.practicum.pageobject.Constants.*;

public class PersonalAccountTest extends BaseUITest {

    protected UserData userData;
    protected UserApi userApi = new UserApi();
    protected String accessToken;

    @Before
    public void createUser() {
        userData = getRandomUser("test", "test", "Aleks");
        ValidatableResponse response = userApi.createUser(userData);

        response.log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("success", is(true));
        ValidatableResponse responseLoginUser = userApi.loginUser(userData);
        accessToken = responseLoginUser.extract().jsonPath().get("accessToken").toString().substring(7);
    }

    @Test
    @DisplayName("Check click button login on main page")
    @Description("Can click login button")
    public void checkSwitchPersonalAccountPageTest() {
        open(LOGIN_PAGE_URL, LoginPage.class);
        LoginPage loginPage = new LoginPage();
        loginPage.setEmailUserField(userData.getEmail());
        loginPage.setPasswordUserField(userData.getPassword());
        loginPage.loginButtonClick();
        MainPage mainPage = new MainPage();
        mainPage.personalAccountButtonClick();
        PersonalPage personalPage = new PersonalPage();
        boolean actual = personalPage.isEnabledLogoutButton();
        Assert.assertTrue("Переход по клику на \"Личный кабинет\" не выполнен", actual);
    }

    //true
    @Test
    @DisplayName("Check click constructor button")
    @Description("Can click constructor button")
    public void checkSwitchFromPersonalAccountToClickConstructorTest() {
        open(LOGIN_PAGE_URL, LoginPage.class);
        LoginPage loginPage = new LoginPage();
        loginPage.setEmailUserField(userData.getEmail());
        loginPage.setPasswordUserField(userData.getPassword());
        loginPage.loginButtonClick();
        MainPage mainPage = new MainPage();
        mainPage.personalAccountButtonClick();
        PersonalPage personalPage = new PersonalPage();
        personalPage.constructorButtonClick();
        boolean actual = mainPage.isEnabledPlaceOrderButton();
        Assert.assertTrue("Переход из личного кабинета по клику на \"Конструктор\" не выполнен", actual);
    }

    @Test
    @DisplayName("Check click logo button")
    @Description("Can click logo button")
    public void checkSwitchFromPersonalAccountToLogoBurgersTest() {
        open(LOGIN_PAGE_URL, LoginPage.class);
        LoginPage loginPage = new LoginPage();
        loginPage.setEmailUserField(userData.getEmail());
        loginPage.setPasswordUserField(userData.getPassword());
        loginPage.loginButtonClick();
        MainPage mainPage = new MainPage();
        mainPage.personalAccountButtonClick();
        PersonalPage personalPage = new PersonalPage();
        personalPage.logoBurgersClick();
        boolean actual = mainPage.isEnabledPlaceOrderButton();
        Assert.assertTrue("Переход из личного кабинета по клику на логотип не выполнен", actual);
    }

    @Test
    @DisplayName("Check click button logout on personal page")
    @Description("Can logout")
    public void logoutAccountTest() {
        open(LOGIN_PAGE_URL, LoginPage.class);
        LoginPage loginPage = new LoginPage();
        loginPage.setEmailUserField(userData.getEmail());
        loginPage.setPasswordUserField(userData.getPassword());
        loginPage.loginButtonClick();
        MainPage mainPage = new MainPage();
        mainPage.personalAccountButtonClick();
        PersonalPage personalPage = new PersonalPage();
        personalPage.logoutButtonClick();
        boolean actual = loginPage.isEnabledLoginButton();
        Assert.assertTrue("Выход из аккаунта по кнопке \"Выйти\" не выполнен", actual);
    }

    @After
    public void deleteCreatedUser() {
        ValidatableResponse responseLoginUser = userApi.loginUser(userData);

        if(responseLoginUser.extract().statusCode() == HttpStatus.SC_OK) {
            String accessToken = responseLoginUser.extract().jsonPath().get("accessToken").toString().substring(7);
            ValidatableResponse responseDeleteUser = userApi.deleteUser(accessToken);

            responseDeleteUser.log().all()
                    .assertThat()
                    .statusCode(HttpStatus.SC_ACCEPTED)
                    .body("success", is(true));
        }
    }
}
