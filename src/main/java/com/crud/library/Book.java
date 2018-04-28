package com.crud.library;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;

@Entity(name = "books")
@Getter
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String title;

    @Column
    private String author;
}
