
package model;

public class Bill {
    
    private String billCode;

    private String customerName;
    
    private String createdBuy;
    
    private float total;

    public Bill() {
    }

    public Bill(String billCode, String customerName, String createdBuy, float total) {
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

    public String getCreatedBuy() {
        return createdBuy;
    }

    public void setCreatedBuy(String createdBuy) {
        this.createdBuy = createdBuy;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Bill{" + "billCode=" + billCode + ", customerName=" + customerName + ", createdBuy=" + createdBuy + ", total=" + total + '}';
    }

    
    
    
}
