package com.example.demo;


import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "ILDONG_BOARD")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @NotBlank
    @Size(min=5, max=20)
    private String title;

    @Email
    @Column(name = "email", unique = true)
    private String email;

    private String password;

    private boolean deleted;

    public boolean isDeleted() { return deleted; }

    public void setDeleted(boolean deleted) {  this.deleted = deleted; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) { this.password = password; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email;  }
}
