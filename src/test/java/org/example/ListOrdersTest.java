package org.example;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;

public class ListOrdersTest {

    @Test
    @DisplayName("Orders - Возвращение списка заказов")
    public void orderListReturnTest(){
        CreateOrders createOrders = new CreateOrders();
        ValidatableResponse responseOrdersList = createOrders.orderListReturn();
        responseOrdersList.assertThat().statusCode(SC_OK);
    }
}
