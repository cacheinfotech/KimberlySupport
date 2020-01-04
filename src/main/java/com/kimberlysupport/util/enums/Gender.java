package com.kimberlysupport.util.enums;

import lombok.Getter;

@Getter
public enum Gender {
    Male("Male"),Female("Female"),Other("Other");

    String value;
    Gender(String value){
        this.value=value;
    }

}
