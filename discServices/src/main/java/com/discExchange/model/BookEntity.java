package com.discExchange.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "Books")
//
@Getter
@Setter
@NoArgsConstructor
public class BookEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;


    /**
     *
     *  @see <a href="https://stackoverflow.com/questions/3774198/org-hibernate-mappingexception-could-not-determine-type-for-java-util-list-at">
     *     если аннотированы поля, то использовать на них, а не на методах
     *    </a>
     *
     *
     * */
    //
    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinTable(
            name = "AuthorBookLinks",
            joinColumns = { @JoinColumn(name = "bookId")},
            inverseJoinColumns = { @JoinColumn(name = "authorId") }
    )
    private Set<AuthorEntity> authors;
}
