package com.monetize360.InvoiceGeneration.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Data
@Setter
@Getter
@Builder

public class InvoiceDto {

    private String name;
    @JacksonXmlElementWrapper(localName = "orders")
    @JacksonXmlProperty(localName = "order")
    private List<OrderDto> orders;
    private float totalAmt;
}
