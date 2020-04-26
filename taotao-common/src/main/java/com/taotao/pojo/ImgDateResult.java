package com.taotao.pojo;

import java.io.Serializable;

public class ImgDateResult implements Serializable{
    private int code;
    private String msg;
    private ImgData data;

    @Override
    public String toString() {
        return "ImgDateResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ImgData getData() {
        return data;
    }

    public void setData(ImgData data) {
        this.data = data;
    }
}
