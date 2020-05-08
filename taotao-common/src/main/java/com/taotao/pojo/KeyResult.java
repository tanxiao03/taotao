package com.taotao.pojo;

import java.io.Serializable;

public class KeyResult implements Serializable {
    private int id;
    private String paramName;
    private Long groupId;
    private String paramGroup;

    @Override
    public String toString() {
        return "KeyResult{" +
                "id=" + id +
                ", paramName='" + paramName + '\'' +
                ", groupId=" + groupId +
                ", paramGroup='" + paramGroup + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getParamGroup() {
        return paramGroup;
    }

    public void setParamGroup(String paramGroup) {
        this.paramGroup = paramGroup;
    }
}
