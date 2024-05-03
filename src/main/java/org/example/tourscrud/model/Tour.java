package org.example.tourscrud.model;

public class Tour {
    private int id;
    private String code;
    private String destination;
    private double price;
    private String img;

    public Tour() {
    }

    public Tour(int id, String code, String destination, double price, String img) {
        this.id = id;
        this.code = code;
        this.destination = destination;
        this.price = price;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "tour{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", destination='" + destination + '\'' +
                ", price=" + price +
                ", img='" + img + '\'' +
                '}';
    }
}
