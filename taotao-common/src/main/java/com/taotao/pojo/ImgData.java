package com.taotao.pojo;

import java.io.Serializable;

public class ImgData implements Serializable{
    private String src;

    @Override
    public String toString() {
        return "ImgData{" +
                "src='" + src + '\'' +
                '}';
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
