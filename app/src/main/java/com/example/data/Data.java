package com.example.data;

public class Data {
    String id, title, price, type, images, description, address, Data, room, date;
    int icon;
    public Data(){

    }

    public Data(String id, String title, String price, String images, String address, String type, String description, String data,String room, String date,int icon){
        this.id = id;
        this.title = title;
        this.price = price;
        this.images = images;
        this.address = address;
        this.type = type;
        this.description = description;
        this.Data = data;
        this.room = room;
        this.date = date;
        this.icon = icon;
    }

    public int getIcon() {
        return icon;
    }

    public String getDate() {
        return date;
    }

    public String getRoom() {
        return room;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getData() {
        return Data;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImages() {
        return images;
    }

    public String getType() {
        return type;
    }
}
