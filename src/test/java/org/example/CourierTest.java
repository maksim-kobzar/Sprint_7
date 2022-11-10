package org.example;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


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
    @DisplayName("Создание курьера - позитивные проверки")
    public void createCourierTest(){
        ValidatableResponse responseCreate = createCourier.create(courier);//создание курьера
        ValidatableResponse responseLogin = createCourier.login(Credentials.from(courier)); //авторизация курьера
        ValidatableResponse responseCreateDuplicate = createCourier.createDuplicate(courier); //дубликат
        ValidatableResponse responseCreateOne = createCourier.createOnlyLogin(courier.getLogin()); //Заполнен только логин

        id = responseLogin.extract().path("id");
        int responseCreateCode = responseCreate.extract().statusCode();
        int responseLoginCode = responseLogin.extract().statusCode();
        boolean responseOk = responseCreate.extract().path("ok");
        int responseCreateDuplicateCode = responseCreateDuplicate.extract().statusCode();
        int responseCreateOneCode = responseCreateOne.extract().statusCode();

        Assert.assertEquals("Ошибка при создании курьера" ,responseCreateCode, 201);
        Assert.assertEquals("Ошибка при входе в аккаунт", responseLoginCode, 200);
        Assert.assertTrue("Ошибка в теле сообщения после создания курьера", responseOk);
        Assert.assertEquals("Ошибка при создании дубликата курьера", responseCreateDuplicateCode, 409);
        Assert.assertEquals("Ошибка при создании курьера с одним обязательным полем", responseCreateOneCode, 400);
    }

}