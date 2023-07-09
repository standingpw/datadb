package com.metrix.orm.entity;

import java.util.Date;

public class User {
    private int id;
    private String username;
    private String password;
    private String phone;
    private Date birthday;
    private double money;
    private int gender;
    public String  method1(int a,int b){
        int c = a+b;
        System.out.println("a+b="+c);
        return "a+b";
    }
    public String  method1(){
        return "a+b";
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public User() {
    }

    public User(int id, String username, String password, String phone, Date birthday, double money, int gender) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.birthday = birthday;
        this.money = money;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", birthday=" + birthday +
                ", money=" + money +
                ", gender=" + gender +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }


}
