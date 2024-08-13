package com.monetize360.InvoiceGeneration.service;

import com.monetize360.InvoiceGeneration.domain.Dessert;
import com.monetize360.InvoiceGeneration.domain.Drink;
import com.monetize360.InvoiceGeneration.domain.Menu;
import com.monetize360.InvoiceGeneration.dto.InvoiceDto;
import com.monetize360.InvoiceGeneration.dto.OrderDto;

import java.util.List;

public interface OrderProcessorInterface {
    Dessert findDessertById(Menu menu, int id);
    Drink findDrinkById(Menu menu, int id);
    OrderDto createOrderDto(int id, String name, int quantity, float price);
    InvoiceDto getOrderSummary(List<OrderDto> orders);
}
