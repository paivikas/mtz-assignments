package com.monetize360.InvoiceGeneration.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.monetize360.InvoiceGeneration.domain.Dessert;
import com.monetize360.InvoiceGeneration.domain.Drink;
import com.monetize360.InvoiceGeneration.domain.Menu;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class JsonReaderUtil {

    private JsonReaderUtil() {
    }

    public static Menu jsonToMenuObject() {
        Menu menu = new Menu();
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = JsonReaderUtil.class.getClassLoader().getResourceAsStream("menu.json")) {

            if (inputStream == null) {
                throw new RuntimeException("JSON file not found in resources folder");
            }

            JsonNode rootNode = objectMapper.readTree(new InputStreamReader(inputStream));
            JsonNode dessertsNode = rootNode.path("menu").path("desserts");
            JsonNode drinksNode = rootNode.path("menu").path("drinks");


            List<Dessert> desserts = objectMapper.convertValue(dessertsNode, objectMapper.getTypeFactory().constructCollectionType(List.class, Dessert.class));
            int i = 1;
            List<Drink> drinks = objectMapper.convertValue(drinksNode, objectMapper.getTypeFactory().constructCollectionType(List.class, Drink.class));
            for (Drink drink : drinks) {
                drink.setId(i++);
            }
            for (Dessert dessert : desserts) {
                dessert.setId(i++);
            }


            menu.setDesserts(desserts);
            menu.setDrinks(drinks);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return menu;
    }

    public static void menuObjectToXML() {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            String xml = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonToMenuObject());
            System.out.println(xml);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
