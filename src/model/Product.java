package model;

public class Product {

    private String productId;
    
    private String name;
        
    private float price;
    
    private String category;
    
    private String detail;
    public Product() {
    }
    
//    private byte [] image;

//    public byte[] getImage() {
//        return image;
//    }
//
//    public void setImage(byte[] image) {
//        this.image = image;
//    }

    
     public Product(String productId, String name, float price, String category, String detail) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
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

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", name=" + name + ", price=" + price + ", category=" + category + ", detail=" + detail + '}';
    }
    
    
}
