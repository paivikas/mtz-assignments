package com.monetize360.jpa.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@AllArgsConstructor
@SuperBuilder
@Entity
//@Table(name="AUTHOR_TBL")
public class Author extends BaseEntity{
    //@Id
    //@GeneratedValue
    //@SequenceGenerator(name = "author_sequence", sequenceName = "author_sequence", allocationSize = 1)
    //@TableGenerator(name = "author_id_gen",table = "id_generator",pkColumnName = "id_name",valueColumnName = "id_value",allocationSize = 1)
    //private Integer id;
    //@Column(name = "f_name", length = 35)
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    private int age;
    @ManyToMany(mappedBy = "authors")
    private List<Course> courses;
    //@Column(updatable = false, nullable = false)
//    private LocalDateTime createdAt;
//    @Column(insertable = false)
//    private LocalDateTime lastModified;
}
