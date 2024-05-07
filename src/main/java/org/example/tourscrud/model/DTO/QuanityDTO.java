package org.example.tourscrud.model.DTO;

public class QuanityDTO {
    private String type;
    private int quanity;

    public QuanityDTO(String type, int quanity) {
        this.type = type;
        this.quanity = quanity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuanity() {
        return quanity;
    }

    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }
}
