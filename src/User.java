import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt; // Ensure bcrypt library is added to your project

public class User {
    private long id;
    private final String username;
    private final String email;
    private final String passwordHash; // Renamed to avoid conflict

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt()); // Hash the password
        this.id = -1; // Indicates the user has not been added yet
    }

    public void addUser() {
        String url = "jdbc:mysql://127.0.0.1:3306/libirary";
        String dbUser = "root";
        String dbPassword = "1234";

        String insertUserSQL = "INSERT INTO user (username, email, password) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(insertUserSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, passwordHash); // Use renamed field
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    this.id = generatedKeys.getLong(1);
                }
            }

        } catch (SQLException ex) {
            System.err.println("SQL error while adding user: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Unexpected error while adding user: " + ex.getMessage());
        }
    }

    public void deleteUser(long userId) { // Renamed parameter to avoid conflict
        String url = "jdbc:mysql://127.0.0.1:3306/libirary";
        String dbUser = "root";
        String dbPassword = "1234";

        String deleteUserSQL = "DELETE FROM user WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(deleteUserSQL)) {

            stmt.setLong(1, userId); // Use renamed parameter
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("SQL error while deleting user: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Unexpected error while deleting user: " + ex.getMessage());
        }
    }

    public void borrowBook(long bookId, long userId) {
        String url = "jdbc:mysql://127.0.0.1:3306/libirary";
        String dbUser = "root";
        String dbPassword = "1234";

        String borrowBookSQL = "INSERT INTO loan (book_id, user_id, status) VALUES (?, ?, 'on_loan')";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(borrowBookSQL)) {

            stmt.setLong(1, bookId);
            stmt.setLong(2, userId);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("SQL error while borrowing book: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Unexpected error while borrowing book: " + ex.getMessage());
        }
    }

    // Optionally, add getters for `id`, `username`, `email`, and `passwordHash`
    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() { // Renamed getter to match renamed field
        return passwordHash;
    }
}
