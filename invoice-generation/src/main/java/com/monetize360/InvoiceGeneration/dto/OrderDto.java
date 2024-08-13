package com.monetize360.InvoiceGeneration.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;


@Getter
@Setter
@Data
@AllArgsConstructor
@Builder
@JacksonXmlRootElement(localName = "order")
public class OrderDto {
    private int id;
    private String name;
    private int quantity;
    private float price;
    private float totalPrice;
}


