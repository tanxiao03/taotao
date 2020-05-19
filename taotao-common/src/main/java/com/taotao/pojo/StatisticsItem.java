package com.taotao.pojo;

import java.io.Serializable;

public class StatisticsItem implements Serializable{
    private String name;
    private Integer value;

    @Override
    public String toString() {
        return "StatisticsItem{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    public StatisticsItem() {
    }

    public StatisticsItem(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
