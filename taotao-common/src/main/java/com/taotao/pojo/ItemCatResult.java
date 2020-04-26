package com.taotao.pojo;

import java.io.Serializable;

public class ItemCatResult implements Serializable{
    private Long id;
    private String name;
    private boolean isParent;

    @Override
    public String toString() {
        return "ItemCatResult{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isParent=" + isParent +
                '}';
    }

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }
    public boolean getIsParent() {
        return isParent;
    }
}
