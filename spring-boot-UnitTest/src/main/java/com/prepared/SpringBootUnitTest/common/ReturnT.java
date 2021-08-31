package com.prepared.SpringBootUnitTest.common;

/**
 * @Author: prepared
 * @Date: 2021/6/8 10:31
 */
public class ReturnT<T> {

    /**
     * 0 成功 999 失败
     */
    private int code;

    private String message;

    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static ReturnT error(String message) {
        ReturnT returnT = new ReturnT();
        returnT.setCode(999);
        returnT.setMessage(message);
        return returnT;
    }

    public static ReturnT success() {
        ReturnT returnT = new ReturnT();
        returnT.setCode(0);
        return returnT;
    }

    public static <T> ReturnT success(T data) {
        ReturnT returnT = new ReturnT();
        returnT.setCode(0);
        returnT.setData(data);
        return returnT;
    }

    public static ReturnT error() {
        ReturnT returnT = new ReturnT();
        returnT.setCode(999);
        return returnT;
    }
}
