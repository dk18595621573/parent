package com.cloud.component.huasheng.response;

import java.io.Serializable;

/**
 *  华盛返回结果
 *
 * */
public class Backtrack<T> implements Serializable {
    private int code;
    private String msg;
    private Boolean data = true;

    private Backtrack(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Backtrack(int code, String msg, Boolean data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public boolean ok() {
        return 0 == this.getCode();
    }

    public static <T> Backtrack<T> result(boolean result) {
        return result ? success() : error();
    }

    public static <T> Backtrack<T> success() {
        return success("操作成功");
    }

    public static <T> Backtrack<T> data(Boolean data) {
        return success("操作成功", data);
    }

    public static <T> Backtrack<T> success(String msg) {
        return new Backtrack(0, msg);
    }

    public static <T> Backtrack<T> success(String msg, Boolean data) {
        return new Backtrack(0, msg, data);
    }

    public static <T> Backtrack<T> error() {
        return error("操作失败");
    }

    public static <T> Backtrack<T> error(String msg) {
        return error(msg, false);
    }

    public static <T> Backtrack<T> error(String msg, Boolean data) {
        return new Backtrack(500, msg, data=false);
    }

    public static <T> Backtrack<T> error(int code, String msg) {
        return new Backtrack(code, msg);
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public Boolean getData() {
        return this.data;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }

    public void setData(final Boolean data) {
        this.data = data;
    }

    public String toString() {
        int var10000 = this.getCode();
        return "Backtrack(code=" + var10000 + ", msg=" + this.getMsg() + ", data=" + this.getData() + ")";
    }
}
