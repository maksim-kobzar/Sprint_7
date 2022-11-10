package org.example;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        ValidatableResponse responseCreate = createCourier.create(courier);//создание курьера
        ValidatableResponse responseLogin = createCourier.login(Credentials.from(courier)); //авторизация курьера
        ValidatableResponse responseLoginOnly = authorization.autorizationOnlyField(courier.getLogin());
        ValidatableResponse responsePasswordOnly = authorization.autorizationOnlyField(courier.getPassword());
        String fakeLogin = "{\"login\": \"sss\", \"password\": \"3344551\"}";;
        ValidatableResponse responseLoginIncorrect = authorization.autorizationOnlyField(fakeLogin);

        id = responseLogin.extract().path("id");
        int responseLoginCode = responseLoginOnly.extract().statusCode();
        int responsePasswordCode = responsePasswordOnly.extract().statusCode();
        String responseLoginIncorrectCode = responseLoginIncorrect.extract().path("message");
        Assert.assertEquals("Ошибка при авторизации с одним заполненным полем" ,responseLoginCode, 400);
        Assert.assertEquals("Ошибка при авторизации с одним заполненным полем" ,responsePasswordCode, 400);
        Assert.assertEquals("Ошибка при авторизации с неправильным логином" ,responseLoginIncorrectCode, "Учетная запись не найдена");
    }
}
