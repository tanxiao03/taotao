package com.taotao.pojo;

import java.io.Serializable;

public class Show implements Serializable {
    private Integer value;
    private String name;

    public Show() {
    }

    public Show(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Show{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
