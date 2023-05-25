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
    //su dung id tu sinh 
    private String id;
    
    private String fullname;
    
    private String phone;
    
    private boolean gender;
    
    private String email;
    
    private String password;
    
    private String role;

    public User() {
    }
    
    

    public User(String id, String fullname, String phone, boolean gender, String email, String password, String role) {
        this.id = id;
        this.fullname = fullname;
        this.phone = phone;
        this.gender = gender;
        this.email = email;
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

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
     
}
