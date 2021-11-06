package com.example.demo.Model;


import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String body;
    private int createdAt;
//    @ManyToOne
    @ManyToOne
    @JoinColumn(name = "user_model_id")
    private userModel userModel;

    public void setUserModel(com.example.demo.Model.userModel userModel) {
        this.userModel = userModel;
    }

    public com.example.demo.Model.userModel getUserModel() {
        return userModel;
    }

    public Post(String body, userModel userModel) {
        this.body = body;
        this.userModel=userModel;
    }
    public Post(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }


}
