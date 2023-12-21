package com.example.data;

public class DataSet {
    String title, images, url, address, price, date, count;
    int id;
    public DataSet(){

    }
    public DataSet(String title){
        this.title = title;
    }
    public DataSet(int id, String title){
        this.title = title;
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public DataSet(String title, String images){
        this.title = title;
        this.images = images;
    }

    public DataSet(String title,String images, String url){
        this.title = title;
        this.images = images;
        this.url = url;
    }

    public DataSet(int id, String title,String images, String address, String harga, String date, String count){
        this.id = id;
        this.title = title;
        this.images = images;
        this.address = address;
        this.price = harga;
        this.date = date;
        this.count = count;
    }

    public String getCount() {
        return count;
    }

    public String getDate() {
        return date;
    }

    public DataSet(int id, String title, String date){
        this.id = id;
        this.title = title;
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public String getAddress() {
        return address;
    }

    public String getTitle() {
        return title;
    }

    public String getImages() {
        return images;
    }

    public String getUrl() {
        return url;
    }


}
