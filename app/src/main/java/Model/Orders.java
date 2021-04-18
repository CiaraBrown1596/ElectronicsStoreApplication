package Model;

public class Orders {
    private String address, city, dateTime, name, totalAmount, email;

    public Orders() {
    }

    public Orders(String address, String city, String dateTime, String name, String totalAmount, String email) {
        this.address = address;
        this.city = city;
        this.dateTime = dateTime;
        this.name = name;
        this.totalAmount = totalAmount;
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public Orders setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Orders setCity(String city) {
        this.city = city;
        return this;
    }


    public String getName() {
        return name;
    }

    public Orders setName(String name) {
        this.name = name;
        return this;
    }


    public String getTotalAmount() {
        return totalAmount;
    }

    public Orders setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Orders setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getDateTime() {
        return dateTime;
    }

    public Orders setDateTime(String dateTime) {
        this.dateTime = dateTime;
        return this;
    }
}
