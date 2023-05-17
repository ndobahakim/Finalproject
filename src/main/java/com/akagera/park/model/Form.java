package com.akagera.park.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    public class Form {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        @Column
        private String firstname;
        @Column
        private String email;
        @Column
        private String phonenumber;
        @Column
        private String address;


    }


