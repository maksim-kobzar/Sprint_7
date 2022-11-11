package org.example;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CreateOrders extends Client {

    @Step("Создание заказа")
    public ValidatableResponse orderCreation(Orders orders){
        return given()
                .spec(getSpec())
                .body(orders)
                .when()
                .post("api/v1/orders")
                .then();
    }

    @Step("Список заказов")
    public ValidatableResponse orderListReturn(){
        return given()
                .spec(getSpec())
                .when()
                .get("api/v1/orders")
                .then();
    }
}
