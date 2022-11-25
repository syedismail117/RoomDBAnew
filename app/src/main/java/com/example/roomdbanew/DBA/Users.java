package com.example.roomdbanew.DBA;

//to define the columns and data types in database create table


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Users implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Users(int id, String name, String number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }
}
