package com.kimberlysupport.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Dispenser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String uniqueId= UUID.randomUUID().toString();
    @Column(unique = true)
    private String serialNumber;

    private String address;

    @OneToMany(mappedBy = "dispenser")
    private List<Complain> complains;

}
