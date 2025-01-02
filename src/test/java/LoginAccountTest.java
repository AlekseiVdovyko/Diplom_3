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
import ru.yandex.practicum.pageobject.RecoveryPage;
import ru.yandex.practicum.pageobject.RegisterPage;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.core.Is.is;
import static ru.yandex.practicum.models.UserGenerator.getRandomUser;
import static ru.yandex.practicum.pageobject.Constants.*;

public class LoginAccountTest extends BaseUITest {

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
    public void checkLoginAccountViaLoginAccountButtonTest() {
        open(MAIN_PAGE_URL, MainPage.class);
        MainPage mainPage = new MainPage();
        mainPage.enterAccountButtonClick();
        LoginPage loginPage = new LoginPage();
        loginPage.setEmailUserField(userData.getEmail());
        loginPage.setPasswordUserField(userData.getPassword());
        loginPage.loginButtonClick();
        boolean actual = mainPage.isEnabledPlaceOrderButton();
        Assert.assertTrue("Вход по кнопке \"Войти в аккаунт\" не выполнен", actual);
    }

    @Test
    @DisplayName("Check click button login account")
    @Description("Can login click button")
    public void checkLoginAccountViaPersonalAccountButtonTest() {
        open(MAIN_PAGE_URL, MainPage.class);
        MainPage mainPage = new MainPage();
        mainPage.personalAccountButtonClick();
        LoginPage loginPage = new LoginPage();
        loginPage.setEmailUserField(userData.getEmail());
        loginPage.setPasswordUserField(userData.getPassword());
        loginPage.loginButtonClick();
        boolean actual = mainPage.isEnabledPlaceOrderButton();
        Assert.assertTrue("Вход по кнопке \"Личный кабинет\" не выполнен", actual);
    }

    @Test
    @DisplayName("Check click button login account")
    @Description("Can login click button via registration page")
    public void checkLoginAccountViaRegistrationPageTest() {
        open(REGISTER_PAGE_URL, RegisterPage.class);
        RegisterPage registerPage = new RegisterPage();
        registerPage.loginAccountLinkClick();
        LoginPage loginPage = new LoginPage();
        loginPage.setEmailUserField(userData.getEmail());
        loginPage.setPasswordUserField(userData.getPassword());
        loginPage.loginButtonClick();
        MainPage mainPage = new MainPage();
        boolean actual = mainPage.isEnabledPlaceOrderButton();
        Assert.assertTrue("Вход через кнопку в форме регистрации не выполнен", actual);
    }

    @Test
    @DisplayName("Check click button login page recovery")
    @Description("Can click login button")
    public void checkLoginAccountViaRecoveryPageTest() {
        open(RECOVERY_PAGE_URI, RecoveryPage.class);
        RecoveryPage recoveryPage = new RecoveryPage();
        recoveryPage.loginAccountLinkClick();
        LoginPage loginPage = new LoginPage();
        loginPage.setEmailUserField(userData.getEmail());
        loginPage.setPasswordUserField(userData.getPassword());
        loginPage.loginButtonClick();
        MainPage mainPage = new MainPage();
        boolean actual = mainPage.isEnabledPlaceOrderButton();
        Assert.assertTrue("Вход через кнопку в форме восстановления пароля не выполнен", actual);
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
