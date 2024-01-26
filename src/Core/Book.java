package Core;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private int id;
    private String author;

    public int getId() {
        return id;
    }

    public Book( String title, String author, double price, int count) {
        this.author = author;
        this.title = title;

        this.price = price;
        this.count = count;
        this.members = new ArrayList<>();
    }
    public Book(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    private String title;
    private double price;
    private int count;
    private List<Member> members;
}
