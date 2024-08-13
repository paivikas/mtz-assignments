package com.monetize360.InvoiceGeneration.service;

import com.monetize360.InvoiceGeneration.domain.Dessert;
import com.monetize360.InvoiceGeneration.domain.Drink;
import com.monetize360.InvoiceGeneration.domain.Menu;
import com.monetize360.InvoiceGeneration.dto.InvoiceDto;
import com.monetize360.InvoiceGeneration.dto.OrderDto;
import lombok.Setter;

import java.util.List;

@Setter
public class OrderProcessor implements OrderProcessorInterface {
    private String name;

    public Dessert findDessertById(Menu menu, int id) {
        return menu.getDesserts().stream().filter(d -> d.getId() == id).findFirst().orElse(null);
    }

    public Drink findDrinkById(Menu menu, int id) {
        return menu.getDrinks().stream().filter(d -> d.getId() == id).findFirst().orElse(null);
    }

    public OrderDto createOrderDto(int id, String name, int quantity, float price) {
        float totalPrice = price * quantity;
        return OrderDto.builder().id(id).name(name).quantity(quantity).price(price).totalPrice(totalPrice).build();
    }

    public InvoiceDto getOrderSummary(List<OrderDto> orders) {
        float totalAmt = orders.stream().map(OrderDto::getTotalPrice).reduce(0.0f, Float::sum);
        float gst = totalAmt * 0.05f;
        float totalAmtWithGst = totalAmt + gst;

        return InvoiceDto.builder().name(name).orders(orders).totalAmt(totalAmtWithGst).build();
    }
}
