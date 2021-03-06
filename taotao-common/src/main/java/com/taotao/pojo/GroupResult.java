package com.taotao.pojo;

import java.io.Serializable;
import java.util.List;

public class GroupResult implements Serializable {
    private int id;
    private String groupName;
    private Long itemCatId;
    private List<KeyResult> paramKeys;

    @Override
    public String toString() {
        return "GroupResult{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", itemCatId=" + itemCatId +
                ", paramKeys=" + paramKeys +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getItemCatId() {
        return itemCatId;
    }

    public void setItemCatId(Long itemCatId) {
        this.itemCatId = itemCatId;
    }

    public List<KeyResult> getParamKeys() {
        return paramKeys;
    }

    public void setParamKeys(List<KeyResult> paramKeys) {
        this.paramKeys = paramKeys;
    }
}
