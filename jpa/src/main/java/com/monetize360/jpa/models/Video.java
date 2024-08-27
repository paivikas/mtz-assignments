package com.monetize360.jpa.models;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@AllArgsConstructor
@SuperBuilder
@Entity
@DiscriminatorColumn(name = "V")
public class Video extends Resource{
    private int length;
}
