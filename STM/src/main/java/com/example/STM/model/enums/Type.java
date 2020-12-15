package com.example.STM.model.enums;

import lombok.Getter;

@Getter
public enum Type {
    TASK("TASK"),
    BUG("BUG"),
    FEATURE("FEATURE");

    private final String typeName;

    Type(String typeName){
        this.typeName = typeName;
    }
}
