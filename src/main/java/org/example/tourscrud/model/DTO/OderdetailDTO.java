package org.example.tourscrud.model.DTO;

public class OderdetailDTO {
    private String codeOD;
    private String nameOD;
    private String addOD;
    private String phoneOD;
    private int memberOD;
    private int price;
    private int total;

    public OderdetailDTO(String codeOD, String nameOD, String addOD, String phoneOD, int memberOD, int price, int total) {
        this.codeOD = codeOD;
        this.nameOD = nameOD;
        this.addOD = addOD;
        this.phoneOD = phoneOD;
        this.memberOD = memberOD;
        this.price = price;
        this.total = total;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCodeOD() {
        return codeOD;
    }

    public void setCodeOD(String codeOD) {
        this.codeOD = codeOD;
    }

    public String getNameOD() {
        return nameOD;
    }

    public void setNameOD(String nameOD) {
        this.nameOD = nameOD;
    }

    public String getAddOD() {
        return addOD;
    }

    public void setAddOD(String addOD) {
        this.addOD = addOD;
    }

    public String getPhoneOD() {
        return phoneOD;
    }

    public void setPhoneOD(String phoneOD) {
        this.phoneOD = phoneOD;
    }

    public int getMemberOD() {
        return memberOD;
    }

    public void setMemberOD(int memberOD) {
        this.memberOD = memberOD;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
