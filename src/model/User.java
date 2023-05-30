/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Đoàn Hữu Minh
 */
public class User {
    
    private String id;
    
    private String fullname;
    
    private String phone;
    
    private String gender;
    
    private String username;
    
    private String password;
    
    private String role;

    public User() {
    }
    
   
    public User(String id, String fullname, String phone, String gender, String username, String password, String role) {
        this.id = id;
        this.fullname = fullname;
        this.phone = phone;
        this.gender = gender;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", fullname=" + fullname + ", phone=" + phone + ", gender=" + gender + ", username=" + username + ", password=" + password + ", role=" + role + '}';
    }
     
}
