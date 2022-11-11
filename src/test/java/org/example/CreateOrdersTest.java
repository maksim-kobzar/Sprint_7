package org.example;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class CreateOrdersTest {

    private CreateOrders createOrders;

    private final Orders orders;

    public CreateOrdersTest(Orders orders){
        this.orders = orders;
    }

    @Parameterized.Parameters // добавили аннотацию
    public static Object[][] getSumData() {
        return new Object[][] {
                {OrdersGeneration.getColorBlack()},
                {OrdersGeneration.getColorGrey()},
                {OrdersGeneration.getColorGreyAndBlack()},
                {OrdersGeneration.getOrders()},
        };
    }

    @Before
    public void setUp(){
        createOrders = new CreateOrders();
    }

    @Test()
    @DisplayName("Orders - Создание заказа")
    public void orderCreationTest() {
        ValidatableResponse responseOrders = createOrders.orderCreation(orders);

        int responseOrder = responseOrders.extract().path("track");
        Assert.assertNotNull("Ошибка при создании заказа", responseOrder);
    }
}