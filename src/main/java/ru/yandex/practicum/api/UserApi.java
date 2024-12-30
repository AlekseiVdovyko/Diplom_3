package ru.yandex.practicum.api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.practicum.models.UserData;

import static io.restassured.RestAssured.given;

public class UserApi extends RestApi {

        public static final String CREATE_USER_URI = "/api/auth/register";
        public static final String LOGIN_USER_URI = "/api/auth/login";
        public static final String DELETE_USER_URI = "/api/auth/user";

        @Step("Send POST request create user to /api/auth/register")
        public ValidatableResponse createUser(UserData user) {
                return given()
                        .spec(requestSpecification())
                        .and()
                        .body(user)
                        .when()
                        .post(CREATE_USER_URI)
                        .then();
        }

        @Step("Send POST request login user to /api/auth/login")
        public ValidatableResponse loginUser(UserData user) {
                return given()
                        .spec(requestSpecification())
                        .and()
                        .body(user)
                        .when()
                        .post(LOGIN_USER_URI)
                        .then();
        }

        @Step("Send DELETE request delete user to /api/auth/user")
        public ValidatableResponse deleteUser(String accessToken) {
                return given()
                        .spec(requestSpecification())
                        .auth().oauth2(accessToken)
                        .delete(DELETE_USER_URI)
                        .then();
        }
}
