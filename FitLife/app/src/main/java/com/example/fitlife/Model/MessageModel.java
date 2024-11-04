package com.example.fitlife.Model;

public class MessageModel {
    private String message;
    private boolean type; // true = chat, false = question

    public MessageModel(String message, boolean type) {
        this.message = message;
        this.type = type;
    }

    public MessageModel() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
}
