/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.snapichat.user;

import com.dreamteam.snapichat.helpers.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author John
 */
public class User {
    private final int id;
    private final String username;
    private final String firstName;
    private final String lastName;
    private String email;
    private String country;
    private String city;
    private String phone;
    private final String longitude;
    private final String latitude;

    public User(UserBuilder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.email = builder.email;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.country = builder.country;
        this.city = builder.city;
        this.phone = builder.phone;
        this.longitude = builder.longitude;
        this.latitude = builder.latitude;
    }

    public int getId() {
        return id;
    }
    
    public String getIdAsString() {
        return Integer.toString(id);
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    
    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getPhone() {
        return phone;
    }
    
    public String getLongitude() {
        return longitude;
    }
    
    public String getLatitude() {
        return latitude;
    }

    public void refreshInfo() throws SQLException {
        Connection conn = DBHelper.getConnection();

        String sql = "SELECT * FROM user WHERE username=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, username);

        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            email = rs.getString("user_email");
            country = rs.getString("user_country");
            city = rs.getString("user_city");
            phone = rs.getString("user_phone_num");
        }

        conn.close();
    }

    public static class UserBuilder {

        private final int id;
        private final String username;
        private String email;
        private String firstName;
        private String lastName;
        private String country;
        private String city;
        private String phone;
        private String longitude;
        private String latitude;

        public UserBuilder(int id, String username) {
            this.id = id;
            this.username = username;
            longitude = "0";
            latitude = "0";
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder country(String country) {
            this.country = country;
            return this;
        }
        
        public UserBuilder city(String city) {
            this.city = city;
            return this;
        }
        
        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }
        
        public UserBuilder longitude(String longitude) {
            if(longitude == null) {
                this.longitude = "0";
            } else {
                this.longitude = longitude;
            }
            
            return this;
        }
        
        public UserBuilder latitude(String latitude) {
            if(latitude == null) {
                this.latitude = "0";
            } else {
                this.latitude = latitude;
            }
            
            return this;
        }

        public User createUser() {
            return new User(this);
        }
    }
}
