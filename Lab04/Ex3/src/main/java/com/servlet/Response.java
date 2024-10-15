package com.servlet;

public class Response {
    private int id;
    private String message;
    private Object obj;

    public Response(int id, String message, Object obj) {
        this.id = id;
        this.message = message;
        this.obj = obj;
    }

    public int getId() {
        return this.id;
    }
    public String getMessage() {
        return this.message;
    }
    public Object getObj() {
        return this.obj;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setObj(Object obj) {
        this.obj = obj;
    }
}
