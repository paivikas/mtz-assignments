package com.monetize360;

import com.monetize360.InvoiceGeneration.domain.Dessert;
import com.monetize360.InvoiceGeneration.domain.Drink;
import com.monetize360.InvoiceGeneration.domain.Menu;
import com.monetize360.InvoiceGeneration.dto.InvoiceDto;
import com.monetize360.InvoiceGeneration.dto.OrderDto;
import com.monetize360.InvoiceGeneration.service.JsonReaderUtil;
import com.monetize360.InvoiceGeneration.service.OrderProcessor;
import com.monetize360.InvoiceGeneration.service.PdfGenService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! Please enter your name:");
        String name = sc.nextLine();
        System.out.println("Welcome to Ocean Pearl! " + name + "!");
        System.out.println("Here is the Menu:");


        Menu menu = JsonReaderUtil.jsonToMenuObject();
        int idWidth = 5;
        int nameWidth = 50;
        int priceWidth = 10;

        // Print Drink details
        System.out.printf("%-" + idWidth + "s %-" + nameWidth + "s %-" + priceWidth + "s%n", "ID", "Drinks", "Price");
        System.out.println("-".repeat(idWidth + nameWidth + priceWidth)); // Separator line

        for (Drink drink : menu.getDrinks()) {
            System.out.printf("%-" + idWidth + "d %-" + nameWidth + "s %-" + priceWidth + ".2f%n", drink.getId(), drink.getName(), drink.getPrice());
        }
        System.out.println(); // New line
        // Print Dessert details
        System.out.printf("%-" + idWidth + "s %-" + nameWidth + "s %-" + priceWidth + "s%n", "ID", "Desserts", "Price");
        System.out.println("-".repeat(idWidth + nameWidth + priceWidth)); // Separator line

        for (Dessert dessert : menu.getDesserts()) {
            System.out.printf("%-" + idWidth + "d %-" + nameWidth + "s %-" + priceWidth + ".2f%n", dessert.getId(), dessert.getName(), dessert.getPrice());
        }


        OrderProcessor orderProcessor = new OrderProcessor();
        orderProcessor.setName(name);
        List<OrderDto> orders = new ArrayList<>();

        while (true) {
            System.out.print("Enter ID (or 'done' to finish): ");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("done")) {
                break;
            }

            int id = Integer.parseInt(input);

            System.out.print("Enter Quantity: ");
            int quantity = Integer.parseInt(sc.nextLine());

            Dessert dessert = orderProcessor.findDessertById(menu, id);
            Drink drink = orderProcessor.findDrinkById(menu, id);

            if (dessert != null) {
                orders.add(orderProcessor.createOrderDto(dessert.getId(), dessert.getName(), quantity, dessert.getPrice()));
            } else if (drink != null) {
                orders.add(orderProcessor.createOrderDto(drink.getId(), drink.getName(), quantity, drink.getPrice()));
            } else {
                System.out.println("ID not found.");
            }
        }

        InvoiceDto invoice = orderProcessor.getOrderSummary(orders);


        System.out.println("\nInvoice for: " + invoice.getName());
        System.out.println("-------------------------------------------------");
        System.out.printf("%-10s %-50s %-10s %-10s %-10s%n", "ID", "Item", "Quantity", "Price", "Total");
        System.out.println("-------------------------------------------------");

        for (OrderDto order : invoice.getOrders()) {
            System.out.printf("%-10d %-50s %-10d %-10.2f %-10.2f%n", order.getId(), order.getName(), order.getQuantity(), order.getPrice(), order.getTotalPrice());
        }

        System.out.println("-------------------------------------------------");
        System.out.printf("Total Amount: %-10.2f%n", invoice.getTotalAmt());

        PdfGenService service=new PdfGenService();
        service.saveXmlToFile(invoice);
        service.createPdf();
    }

}
