/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Doan Huu Minh
 */
public class Category {
    private String categoryId;
    
    private String name;
    
    private String detail;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Category() {
    }
    
    public Category(String categoryId, String name, String detail) {
        this.categoryId = categoryId;
        this.name = name;
        this.detail = detail;
    }    

    @Override
    public String toString() {
        return name ;
    }
    
    
    
}
