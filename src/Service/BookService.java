package Service;

import Core.Book;
import Core.Member;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookService {
    public void insertBook(Connection connection, Book book) {
        String sql = "INSERT INTO Books (Title, Author, Price, Count) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setDouble(3, book.getPrice());
            preparedStatement.setInt(4, book.getCount());

            preparedStatement.executeUpdate();
            System.out.println("Book inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteBook(Connection connection, int bookID) {
        String sql = "DELETE FROM Books WHERE BookID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, bookID);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Book deleted successfully!");
            } else {
                System.out.println("Book not found or delete failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateBook(Connection connection, int bookID, double newPrice, int newCount) {
        String sql = "UPDATE Books SET Price = ?, Count = ? WHERE BookID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDouble(1, newPrice);
            preparedStatement.setInt(2, newCount);
            preparedStatement.setInt(3, bookID);
           preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertMember(Connection connection, Member member) {
        String sql = "INSERT INTO Members (Name) VALUES (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, member.getName());
            preparedStatement.executeUpdate();
            System.out.println("Member inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertBorrowing(Connection connection, int bookID, int memberID, LocalDate borrowDate, LocalDate returnDate) {
        String sql = "INSERT INTO Borrowings (BookID, MemberID, BorrowDate, ReturnDate) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, bookID);
            preparedStatement.setInt(2, memberID);
            preparedStatement.setDate(3, Date.valueOf(borrowDate));
            preparedStatement.setDate(4, Date.valueOf(returnDate));

            preparedStatement.executeUpdate();
            System.out.println("Borrowing record inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Book> listBooks(Connection connection) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("bookid");
                String title = resultSet.getString("title");
                Book book = new Book(id, title);
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }
    public List<Member> listMembers(Connection connection) {
        List<Member> members = new ArrayList<>();

        String sql = "SELECT * FROM members";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("memberid");
                String name = resultSet.getString("name");

                Member member = new Member(id, name);
                members.add(member);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return members;
    }

}
