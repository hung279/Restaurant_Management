/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Doan Huu Minh
 */
public class DanhBa {
    private String ten;
    private String NS;
    private String DC;
    private String SDT;
    private byte [] image;

    public DanhBa(String ten, String NS, String DC, String SDT, byte[] image) {
        this.ten = ten;
        this.NS = NS;
        this.DC = DC;
        this.SDT = SDT;
        this.image = image;
    }

    public DanhBa() {
    }
    
    
     

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNS() {
        return NS;
    }

    public void setNS(String NS) {
        this.NS = NS;
    }

    public String getDC() {
        return DC;
    }

    public void setDC(String DC) {
        this.DC = DC;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    
    
}
