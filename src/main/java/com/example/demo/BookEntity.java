package com.example.demo;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.*;

@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookEntity
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "bookname")
    private String bookname;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "author")
    private String author;



}