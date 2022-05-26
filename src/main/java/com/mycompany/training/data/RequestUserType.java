package com.mycompany.training.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum RequestUserType {

    CREATE(0, "create"),
    UPDATE(1, "update"),
    GET_INFO(2, "getInfo");
    int num;
    String name;

    RequestUserType(int num, String name) {
        this.num = num;
        this.name = name;
    }
}
