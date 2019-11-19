package com.creativeconsillium.drumsforafrica.helaapp.Activity.Model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Ignore any properties that
 * aren't mapped into the database
 */
@IgnoreExtraProperties
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
