import Core.Book;
import Core.Member;
import Service.BookService;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookService bookService = new BookService();
        DbContext dbContext = new DbContext();
        var connection = dbContext.connect("jdbc:postgresql://localhost:5432/postgres", "postgres", "12345");
        if (connection != null) {
            while (true) {
                System.out.println("Library Management System Menu:");
                System.out.println("1. Insert Book");
                System.out.println("2. Delete Book");
                System.out.println("3. Update Book");
                System.out.println("4. Insert Member");
                System.out.println("5. Insert Borrowing");
                System.out.println("6. Update Member");
                System.out.println("7. Delete Member");
                System.out.println("8. Exit");
                System.out.print("Enter your choice (1-8): ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        handleInsertBook(bookService, connection, scanner);
                        break;
                    case 2:
                        handleDeleteBook(bookService, connection, scanner);
                        break;
                    case 3:
                        handleUpdateBook(bookService, connection, scanner);
                        break;
                    case 4:
                        handleInsertMember(bookService, connection, scanner);
                        break;
                    case 5:
                        handleInsertBorrowing(bookService, connection, scanner);
                        break;
                    case 6:
                        System.out.println("Exiting the program.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                }
            }

        }

    }
    private static void handleInsertBook(BookService bookService, Connection connection, Scanner scanner) {
        System.out.println("Enter book title:");
        String title = scanner.nextLine();
        System.out.println("Enter book author:");
        String author = scanner.nextLine();
        System.out.println("Enter book price:");
        double price = scanner.nextDouble();
        System.out.println("Enter book count:");
        int count = scanner.nextInt();
        bookService.insertBook(connection, new Book(title, author, price, count));
    }

    private static void handleDeleteBook(BookService bookService, Connection connection, Scanner scanner) {
        System.out.println("Enter book ID for deletion:");
        int bookID = scanner.nextInt();
        bookService.deleteBook(connection, bookID);
    }

    private static void handleUpdateBook(BookService bookService, Connection connection, Scanner scanner) {
        System.out.println("Enter book ID for update:");
        int bookID = scanner.nextInt();
        System.out.println("Enter new price:");
        double newPrice = scanner.nextDouble();
        System.out.println("Enter new count:");
        int newCount = scanner.nextInt();
        bookService.updateBook(connection, bookID, newPrice, newCount);
    }

    private static void handleInsertMember(BookService bookService, Connection connection, Scanner scanner) {
        System.out.println("Enter member name:");
        String name = scanner.nextLine();
        bookService.insertMember(connection, new Member(name));
    }

    private static void handleInsertBorrowing(BookService bookService, Connection connection, Scanner scanner) {
        try {
            for (Book book : bookService.listBooks(connection)) {
                System.out.println(book.getId() + ": " + book.getTitle());
            }
            System.out.println("Enter book ID for borrowing:");
            int bookID = scanner.nextInt();
            if (bookID <= 0) {
                System.out.println("Invalid book ID. Please enter a positive integer.");
                return;
            }
             for (Member member:bookService.listMembers(connection)){
                 System.out.println(member.getId()+": " +member.getName());
             }
            System.out.println("Enter member ID for borrowing:");
            int memberID = scanner.nextInt();

            if (memberID <= 0) {
                System.out.println("Invalid member ID. Please enter a positive integer.");
                return;
            }

            System.out.println("Enter borrow date (yyyy-mm-dd):");
            LocalDate borrowDateString = LocalDate.parse(scanner.next());
            System.out.println("Enter return date (yyyy-mm-dd):");
            LocalDate returnDateString = LocalDate.parse(scanner.next());
            bookService.insertBorrowing(connection, bookID, memberID, borrowDateString, returnDateString);
        } catch (InputMismatchException | IllegalArgumentException e) {
            System.out.println("Invalid input. Please enter the correct format.");

    }


}
}