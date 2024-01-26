package Core;
import java.util.ArrayList;
import java.util.List;
public class Member {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", books=" + books +
                '}';
    }

    public Member(String name) {
        this.id = id;
        this.name = name;
        this.books = new ArrayList<>();
    }
    public Member(int id,String name) {
        this.id = id;
        this.name = name;
        this.books = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    private String name;
    private   List<Book> books;
}