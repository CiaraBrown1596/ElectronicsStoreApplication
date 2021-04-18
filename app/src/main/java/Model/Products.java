package Model;

public class Products {

    private String ProductName, ProductId, Category, Date, Image, ProductDescription, Time, Price;

    public Products() {
    }

    public Products(String category,String date,String time,String productId,String price,String productImage,String productName,String productDescription) {
        Category = category;
        Time = time;
        Date = date;
        Price = price;
        ProductDescription = productDescription;
        ProductId = productId;
        ProductName = productName;
        Image = productImage;
    }

    public String getCategory() {
        return Category;
    }

    public Products setCategory(String category) {
        Category = category;
        return this;
    }

    public String getDate() {
        return Date;
    }

    public Products setDate(String date) {
        Date = date;
        return this;
    }

    public String getImage() {
        return Image;
    }

    public Products setImage(String image) {
        Image = image;
        return this;
    }

    public String getPrice() {
        return Price;
    }

    public Products setPrice(String price) {
        Price = price;
        return this;
    }

    public String getTime() {
        return Time;
    }

    public Products setTime(String time) {
        Time = time;
        return this;
    }

    public String getProductName() {
        return ProductName;
    }

    public Products setProductName(String productName) {
        ProductName = productName;
        return this;
    }

    public String getProductDescription() {
        return ProductDescription;
    }

    public Products setProductDescription(String productDescription) {
        ProductDescription = productDescription;
        return this;
    }

    public String getProductId() {
        return ProductId;
    }

    public Products setProductId(String productId) {
        ProductId = productId;
        return this;
    }

}
