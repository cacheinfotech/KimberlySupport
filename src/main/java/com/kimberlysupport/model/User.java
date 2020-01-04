package com.kimberlysupport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kimberlysupport.util.enums.Gender;
import com.kimberlysupport.util.enums.Role;
import com.kimberlysupport.util.enums.UserStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String uniqueId = UUID.randomUUID().toString();
    @Column(nullable = false)
    private String name;
    private String nikname;
    private String workProfile;

    @Column(unique = true)
    private String mobile;

    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String profilePicSrc;
    private String wallPicSrc;

    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;
    @CreatedDate
    private LocalDate dateCreated;
    @LastModifiedDate
    private LocalDate lastUpdated;



    @PrePersist
    public void beforeCreate() {
        this.dateCreated = LocalDate.now();
        this.lastUpdated = LocalDate.now();
    }

    @PreUpdate
    public void beforeUpdate() {
        this.lastUpdated = LocalDate.now();
    }

    @JsonIgnore
    public String getPronoun() {
        if (this.gender != null) {
            if (this.gender.equals(Gender.Male))
                return " his ";
            else
                return " her ";
        }else return  "his/her";
    }

}
