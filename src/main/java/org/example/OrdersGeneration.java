package org.example;

public class OrdersGeneration {

    static String[] color;
    public static Orders getOrders(){
        return new Orders(
                "Maxim",
                "Kononov",
                "Novosibirsk, 142 apt.",
                4,
                "+7 999 999 99 99",
                5,
                "2022-06-06",
                "Привези заказ как можно быстрее",
                null
        );
    }
    public static Orders getColorBlack(){
        Orders orders = OrdersGeneration.getOrders();
        orders.setColor(new String[]{"BLACK"});
        return orders;
    }
    public static Orders getColorGrey(){
        Orders orders = OrdersGeneration.getOrders();
        orders.setColor(new String[]{"GRAY"});
        return orders;
    }

    public static Orders getColorGreyAndBlack(){
        Orders orders = OrdersGeneration.getOrders();
        orders.setColor(new String[]{"BLACK", "GRAY"});
        return orders;
    }

}
