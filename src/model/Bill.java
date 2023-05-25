
package model;

import java.util.Date;

public class Bill {
    //Random Id 
    private String billCode;

    private String customerName;
    
    private Date createdBuy;
    
    private float total;

    public Bill() {
    }

    public Bill(String billCode, String customerName, Date createdBuy, float total) {
        this.billCode = billCode;
        this.customerName = customerName;
        this.createdBuy = createdBuy;
        this.total = total;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getCreatedBuy() {
        return createdBuy;
    }

    public void setCreatedBuy(Date createdBuy) {
        this.createdBuy = createdBuy;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
