package com.url.shortener.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "urlData")
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String longUrl;

    @Column(nullable = false)
    private LocalDate createdDate;

    @Column(nullable = false)
    private LocalDate expiresDate;
}
