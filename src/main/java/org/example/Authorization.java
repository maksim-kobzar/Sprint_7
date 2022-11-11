package org.example;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class Authorization extends Client {
    @Step("Авторизация с невалидными значениями")
    public ValidatableResponse autorizationOnlyField(String json){
        return given()
                .spec(getSpec())
                .body(json)
                .when()
                .post("api/v1/courier/login")
                .then();
    }
}
