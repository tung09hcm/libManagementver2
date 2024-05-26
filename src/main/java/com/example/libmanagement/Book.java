package com.example.libmanagement;

public class Book {
    private String name;
    private String author;
    private String ImageSrc;
    private boolean TextBook;
    private boolean WorkBook;
    private boolean Reference_book;
    private boolean Guidebook;
    private boolean Supplementary;
    public Book()
    {

    }
    public Book(String name, String author, String imageSrc, boolean textBook, boolean workBook, boolean reference_book, boolean guidebook, boolean supplementary) {
        this.name = name;
        this.author = author;
        ImageSrc = imageSrc;
        TextBook = textBook;
        WorkBook = workBook;
        Reference_book = reference_book;
        Guidebook = guidebook;
        Supplementary = supplementary;
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

    public boolean isTextBook() {
        return TextBook;
    }

    public void setTextBook(boolean textBook) {
        TextBook = textBook;
    }

    public boolean isWorkBook() {
        return WorkBook;
    }

    public void setWorkBook(boolean workBook) {
        WorkBook = workBook;
    }

    public boolean isReference_book() {
        return Reference_book;
    }

    public void setReference_book(boolean reference_book) {
        Reference_book = reference_book;
    }

    public boolean isGuidebook() {
        return Guidebook;
    }

    public void setGuidebook(boolean guidebook) {
        Guidebook = guidebook;
    }

    public boolean isSupplementary() {
        return Supplementary;
    }

    public void setSupplementary(boolean supplementary) {
        Supplementary = supplementary;
    }
}
