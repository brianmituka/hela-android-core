package com.creativeconsillium.drumsforafrica.helaapp.Activity.Model;

public class User {
   public String phone;
   public String userId;

    public User() {
    }

    public User(String phone, String userId) {
        this.phone = phone;
        this.userId = userId;
    }
    public User(String phone) {
        this.phone = phone;
        this.userId = userId;
    }
}
