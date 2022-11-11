package org.example;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;

public class CourierTest {
    private CreateCourier createCourier;
    private Courier courier;
    private int id;

    @Before
    public void setUp(){
        createCourier = new CreateCourier();
        courier = CourierGenerator.getDefault();
    }

    @After
    public void deleteCourier(){
        createCourier.deleteCourier(id);
    }

    @Test
    @DisplayName("Создание курьера - Создание курьера")
    public void createCourierTest(){
        ValidatableResponse responseCreate = createCourier.createCourier(courier);//создание курьера
        ValidatableResponse responseLogin = createCourier.login(Credentials.from(courier)); //авторизация курьера

        id = responseLogin.extract().path("id");
        responseCreate.assertThat().statusCode(SC_CREATED);
        responseLogin.assertThat().statusCode(SC_OK);
        responseCreate.body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Авторизация курьера с одним полем логин")
    public void createCourierOnlyLoginTest(){
        ValidatableResponse responseCreate = createCourier.createCourier(courier);//создание курьера
        ValidatableResponse responseLogin = createCourier.login(Credentials.from(courier)); //авторизация курьера
        ValidatableResponse responseCreateOne = createCourier.createOnlyLogin(courier.getLogin()); //Заполнен только логин

        id = responseLogin.extract().path("id");
        responseCreateOne.assertThat().statusCode(SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("Создание дубликата курьера")
    public void createCourierDublicateTest(){
        ValidatableResponse responseCreate = createCourier.createCourier(courier);//создание курьера
        ValidatableResponse responseLogin = createCourier.login(Credentials.from(courier)); //авторизация курьера
        ValidatableResponse responseCreateDuplicate = createCourier.createCourier(courier); //дубликат

        id = responseLogin.extract().path("id");
        responseCreateDuplicate.assertThat().statusCode(SC_CONFLICT);
    }
}