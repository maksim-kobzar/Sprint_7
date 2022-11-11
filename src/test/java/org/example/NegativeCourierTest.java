package org.example;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.hamcrest.Matchers.equalTo;

public class NegativeCourierTest {
    private CreateCourier createCourier;
    private Courier courier;
    private Authorization authorization;
    private int id;

    @Before
    public void setUp(){
        createCourier = new CreateCourier();
        courier = CourierGenerator.getDefault();
        authorization = new Authorization();
    }

    @After
    public void deleteCourier(){
        createCourier.deleteCourier(id);
    }

    @Test
    @DisplayName("Логин курьера - негативные проверки ")
    public void loginCourierNegativeTest(){
        ValidatableResponse responseCreate = createCourier.createCourier(courier);//создание курьера
        ValidatableResponse responseLogin = createCourier.login(Credentials.from(courier)); //авторизация курьера
        ValidatableResponse responseLoginOnly = authorization.autorizationOnlyField(courier.getLogin());
        ValidatableResponse responsePasswordOnly = authorization.autorizationOnlyField(courier.getPassword());
        String fakeLogin = "{\"login\": \"sss\", \"password\": \"3344551\"}";;
        ValidatableResponse responseLoginIncorrect = authorization.autorizationOnlyField(fakeLogin);

        id = responseLogin.extract().path("id");
        responseLoginOnly.assertThat().statusCode(SC_BAD_REQUEST);
        responsePasswordOnly.assertThat().statusCode(SC_BAD_REQUEST);
        responseLoginIncorrect.assertThat().body("message", equalTo("Учетная запись не найдена"));
    }
}
