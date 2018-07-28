package com.lyl.smzdk.model;

public class BaseCallBack<T> {
    public int code = 0;
    public String msg = "";
    public T data;

    public BaseCallBack(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
