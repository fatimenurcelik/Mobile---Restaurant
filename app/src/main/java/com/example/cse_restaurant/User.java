package com.example.cse_restaurant;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
        public String fullname;
        public String email;
        public String password;
        public String phone;

        public User(String fullname, String email, String password, String phone) {
            this.fullname = fullname;
            this.email = email;
            this.password = password;
            this.phone = phone;
        }

    }
