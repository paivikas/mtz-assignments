package com.monetize360.InvoiceGeneration.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class Menu {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "drink")
    private List<Drink> drinks;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "dessert")
    private List<Dessert> desserts;
}
