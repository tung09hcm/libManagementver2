package com.example.libmanagement;

public class Book {
    private String name;
    private String author;
    private String ImageSrc;
    private String id;
    private String returndate;
    private int amount;

    public Book()
    {}

    public Book(String name, String author, String imageSrc, String id, int amount) {
        this.name = name;
        this.author = author;
        this.ImageSrc = imageSrc;
        this.id = id;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) { this.amount = amount; }
    public String getReturndate() {
        return returndate;
    }
    public void setReturndate(String returndate) {
        this.returndate = returndate;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getImageSrc() {
        return ImageSrc;
    }
    public void setImageSrc(String imageSrc) {
        ImageSrc = imageSrc;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
