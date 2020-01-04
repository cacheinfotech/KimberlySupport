package com.kimberlysupport.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Complain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String problem;
    String description;
    LocalDate createdOn=LocalDate.now();

    @ManyToOne
    @JoinColumn
    private Dispenser dispenser;
}
