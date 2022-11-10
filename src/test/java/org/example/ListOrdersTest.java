package org.example;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;

public class ListOrdersTest {

    @Test
    @DisplayName("Orders - Возвращение списка заказов")
    public void orderListReturnTest(){
        CreateOrders createOrders = new CreateOrders();
        ValidatableResponse responseOrdersList = createOrders.orderListReturn();
        int responseListOrders = responseOrdersList.extract().statusCode();
        Assert.assertEquals("Ошибка при получении списка заказов", responseListOrders, 200);
    }
}
