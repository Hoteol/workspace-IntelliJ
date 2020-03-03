package com.example.demo.board.domain;

import java.util.List;

public class BoardDto {
    private int id;
    private String title;
    private List<Reply> reply;

    private int count;

    public int getReplyCount(){
        if(this.reply == null){
            return 0;
        }
        return reply.size();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Reply> getReply() {
        return reply;
    }

    public void setReply(List<Reply> reply) {
        this.reply = reply;
    }

    public int getCount() {
        return getReplyCount();
    }

    public void setCount(int count) {
        this.count = count;
    }
}
