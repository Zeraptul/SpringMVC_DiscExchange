package com.discExchange.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Authors")
//
@Getter
@Setter
@NoArgsConstructor
public class AuthorEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "authors")
    private Set<BookEntity> books;
}
