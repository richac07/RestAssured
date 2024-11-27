package com.qa.data;

//POJO Class - old java class
public class Users {
    String name;
    String job;
    String id;
    String createdAt;

    public String getName() {
        return name;
    }

    public String getJob(){
        return job;
    }
    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setJob(){
        this.job = job;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }


    public Users(){

    }

    public Users(String name, String job){
        this.name=name;
        this.job= job;
    }

}
