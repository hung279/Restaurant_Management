package model;

public class Product {
    //Id nhap vao man hinh 
    private String productId;
    
    private String name;
    
    private String ProductType;
    
    private float price;

    private String detail;
    public Product() {
    }

    
    public Product(String productId, String name, String productType, float price, String detail) {
        this.productId = productId;
        this.name = name;
        this.ProductType = productType;
        this.price = price;
        this.detail = detail;
    }

    
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String ProductType) {
        this.ProductType = ProductType;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    
    
}
