package org.example.tourscrud.model;

public class Customers {
    private int id;
    private String name;
    private int age;
    private String address;
    private String phone;

    private int codeTour;

    public Customers(int id, String name, int age, String address, String phone, int codeTour) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.phone = phone;
        this.codeTour = codeTour;
    }

    public int getCodeTour() {
        return codeTour;
    }

    public void setCodeTour(int codeTour) {
        this.codeTour = codeTour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
