import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import ru.yandex.practicum.api.UserApi;
import ru.yandex.practicum.models.UserData;
import ru.yandex.practicum.pageobject.LoginPage;
import ru.yandex.practicum.pageobject.RegisterPage;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.core.Is.is;
import static ru.yandex.practicum.models.UserGenerator.getRandomUser;
import static ru.yandex.practicum.pageobject.Constants.REGISTER_PAGE_URL;

public class RegistrationUserTest extends BaseUITest{

    protected UserData userData;
    protected UserApi userApi = new UserApi();

    @Test
    @DisplayName("Check registration new user")
    @Description("Can successfully registration user")
    public void successfullyRegistrationTest() {
        open(REGISTER_PAGE_URL, RegisterPage.class);
        userData = getRandomUser("test", "test","test");
        RegisterPage registerPage = new RegisterPage();
        registerPage.setNameUserField(userData.getName());
        registerPage.setEmailUserField(userData.getEmail());
        registerPage.setPasswordUserField(userData.getPassword());
        registerPage.confirmRegisterButtonClick();
        LoginPage loginPage = new LoginPage();
        boolean actual = loginPage.isEnabledLoginButton();
        Assert.assertTrue("Регистрация нового пользователя не выполнена", actual);
    }

    @Test
    @DisplayName("Check can not registration user with wrong password")
    @Description("Can't registration user with wrong password")
    public void checkUnsuccessfulRegistrationTest() {
        open(REGISTER_PAGE_URL, RegisterPage.class);
        userData = getRandomUser("test", "test","");
        RegisterPage registerPage = new RegisterPage();
        registerPage.setNameUserField(userData.getName());
        registerPage.setEmailUserField(userData.getEmail());
        registerPage.setPasswordUserField(userData.getPassword());
        registerPage.confirmRegisterButtonClick();
        boolean actual = registerPage.isIncorrectPasswordText();
        Assert.assertTrue("Нет оповещения о неверном пароле при регистрации", actual);
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
