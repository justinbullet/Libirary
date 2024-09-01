import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private long id;
    private final String title;

    public Book(String title) {
        this.title = title;
        this.id = -1; // Indicates the book has not been added yet
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return this.title;
    }

    public void addBook() {
        String url = "jdbc:mysql://127.0.0.1:3306/libirary";
        String user = "root";
        String password = "1234";

        String insertBookSQL = "INSERT INTO book (title) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(insertBookSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, this.title);
            stmt.executeUpdate(insertBookSQL);

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    this.id = generatedKeys.getLong(1);
                }
            }

        } catch (SQLException ex) {
            System.err.println("SQL error while adding book: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Unexpected error while adding book: " + ex.getMessage());
        }
    }

    public void removeBook(long id) {
        String url = "jdbc:mysql://127.0.0.1:3306/libirary";
        String user = "root";
        String password = "1234";

        String deleteBookSQL = "DELETE FROM book WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(deleteBookSQL)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("SQL error while removing book: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Unexpected error while removing book: " + ex.getMessage());
        }
    }

    public static List<Book> searchBooks(String title) {
        List<Book> books = new ArrayList<>();

        String url = "jdbc:mysql://127.0.0.1:3306/libirary";
        String user = "root";
        String password = "1234";

        String searchBooksSQL = "SELECT * FROM book WHERE title LIKE ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(searchBooksSQL)) {

            stmt.setString(1, "%" + title + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book(rs.getString("title"));
                    book.id = rs.getLong("id");
                    books.add(book);
                }
            }

        } catch (SQLException ex) {
            System.err.println("SQL error while searching books: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Unexpected error while searching books: " + ex.getMessage());
        }

        return books;
    }
}
