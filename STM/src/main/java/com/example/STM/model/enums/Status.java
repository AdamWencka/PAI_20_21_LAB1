package com.example.STM.model.enums;

import lombok.Getter;

@Getter
public enum Status {
    NEW("NOWY"),
    IN_PROGRESS("W TRAKCIE"),
    DONE("ZROBIONE");

    private final String statusName;
    Status(String statusName){
        this.statusName=statusName;
    }
}
