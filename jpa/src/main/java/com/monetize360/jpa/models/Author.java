package com.monetize360.jpa.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@Entity
public class dept {
    @Id
    @GeneratedValue
            //(strategy = GenerationType.TABLE, generator = "author_id_gen")
    //@SequenceGenerator(name = "author_sequence", sequenceName = "author_sequence", allocationSize = 1)
    //@TableGenerator(name = "author_id_gen",table = "id_generator",pkColumnName = "id_name",valueColumnName = "id_value",allocationSize = 1)
    private Integer id;
    @Column
    private String firstName;
    private String lastName;
    private String email;

}
