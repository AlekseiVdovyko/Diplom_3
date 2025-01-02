package ru.yandex.practicum.models;

import io.qameta.allure.Step;
import org.apache.commons.lang.RandomStringUtils;

public class UserGenerator {

    @Step("Generate random user with all parameters")
    public static UserData getRandomUser(String nameParam, String emailParam, String passwordParam) {
        String name = nameParam + RandomStringUtils.randomAlphabetic(5);
        String email = emailParam + RandomStringUtils.randomAlphabetic(3) + "@" + RandomStringUtils.randomAlphabetic(3) + ".ru";
        String password = passwordParam + RandomStringUtils.randomAlphabetic(5);

        return new UserData(name, email, password);
    }
}
