package model;

public class Product {

    private String productId;
    
    private String name;
        
    private float price;
    
    private int quatity;
    
    private String category;
    
    private String detail;
    public Product() {
    }
    

    
     public Product(String productId, String name, float price,int quatity, 
             String category, String detail) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quatity = quatity;
        this.category = category;
        this.detail = detail;
    }

    public int getQuatity() {
        return quatity;
    }

    public void setQuatity(int quatity) {
        this.quatity = quatity;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
