package com.zcx.common.entity;

import java.util.HashMap;

public class MyResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = -8713837118340960775L;

    public MyResponse message(String message) {
        this.put("message", message);
        return this;
    }

    public MyResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    public MyResponse code(Integer code) {
        this.put("code", code);
        return this;
    }

    @Override

    public MyResponse put(String key, Object value) {

        super.put(key, value);

        return this;

    }

    public String getMessage() {

        return String.valueOf(get("message"));

    }

    public Object getData() {

        return get("data");

    }

    public static MyResponse success(String message) {
        MyResponse myResponse = new MyResponse();
        myResponse.put("message", message);
        myResponse.put("code", 200);
        return myResponse;
    }

    public static MyResponse success(String message, Object data) {
        return MyResponse.success(message).put("data", data);
    }

    public static MyResponse buildResult(String message, Integer code) {
        MyResponse myResponse = new MyResponse();
        myResponse.put("message", message);
        myResponse.put("code", code);
        return myResponse;
    }

    public static MyResponse buildResult(String message) {
        MyResponse myResponse = new MyResponse();
        myResponse.put("message", message);
        return myResponse;
    }


    public static MyResponse fail(String message) {
        return MyResponse.buildResult(message, 500);
    }


}
