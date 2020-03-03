package com.example.demo.board.domain;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int parentId;

    @Column(length = 255)
    @NotNull
    @NotBlank
    private String content;

    private boolean deleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
