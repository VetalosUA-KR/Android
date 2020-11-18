package com.vitalii.chatapp;

import java.util.Date;

public class Message {
    private String userName;
    private String textMessage;
    private long messageTime = new Date().getTime();

    public Message() {

    }

    public Message(String userName, String textMessage) {
        this.userName = userName;
        this.textMessage = textMessage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }
}
