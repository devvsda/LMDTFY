package com.devsda.hack.lmdtfy.model;


public class DialogMessage {

    private String contentType;
    private String content;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "contentType='" + contentType + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
