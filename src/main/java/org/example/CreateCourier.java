package org.example;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CreateCourier extends Client {
    @Step("Создание нового пользователя")
    public ValidatableResponse createCourier(Courier courier){
        return given()
                .spec(getSpec())
                .body(courier)
                .when()
                .post("api/v1/courier")
                .then();
    }

    @Step("Вход в аккаунт")
    public ValidatableResponse login(Credentials credentials){
        return given()
                .spec(getSpec())
                .body(credentials)
                .when()
                .post("api/v1/courier/login")
                .then();
    }
    @Step("Удаления пользователя")
    public ValidatableResponse deleteCourier(int id){
        return given()
                .spec(getSpec())
                .when()
                .delete("api/v1/courier/" + id)
                .then();
    }

    @Step("Создание курьера с одним обязательным полем")
    public ValidatableResponse createOnlyLogin(String login){
        return given()
                .spec(getSpec())
                .body(login)
                .when()
                .post("api/v1/courier")
                .then();
    }

    @Step("Создание курьера повторяющимся логином")
    public ValidatableResponse createRepeatedLogin(String login){
        return given()
                .spec(getSpec())
                .body(login)
                .when()
                .post("api/v1/courier")
                .then();
    }
}
