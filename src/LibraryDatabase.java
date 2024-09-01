import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class LibraryDatabase {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/libirary"; // Database URL with database name
    private static final String USER = "root"; // DB username
    private static final String PASS = "1234"; // DB password

    public static void initialize() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            // Create 'user' table
            String sqlUser = "CREATE TABLE IF NOT EXISTS user (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "email VARCHAR(255)," +
                    "name VARCHAR(255)," +
                    "password VARCHAR(255)," +
                    "username VARCHAR(255))";
            stmt.executeUpdate(sqlUser);

            // Create 'room' table
            String sqlRoom = "CREATE TABLE IF NOT EXISTS room (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255))";
            stmt.executeUpdate(sqlRoom);

            // Create 'book' table
            String sqlBook = "CREATE TABLE IF NOT EXISTS book (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "title VARCHAR(255)," +
                    "author VARCHAR(255)," +
                    "added_date DATE," +
                    "description VARCHAR(1023)," +
                    "book_cycle BIGINT," +
                    "available BIT," +
                    "added_by BIGINT," +
                    "room_id BIGINT," +
                    "FOREIGN KEY (added_by) REFERENCES user(id)," +
                    "FOREIGN KEY (room_id) REFERENCES room(id))";
            stmt.executeUpdate(sqlBook);

            // Create 'loan' table
            String sqlLoan = "CREATE TABLE IF NOT EXISTS loan (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "loan_date DATE," +
                    "due_date DATE," +
                    "return_date DATE," +
                    "status ENUM('on_loan', 'overdue', 'returned')," +
                    "student_id BIGINT," +
                    "book_id BIGINT," +
                    "created_by BIGINT," +
                    "FOREIGN KEY (student_id) REFERENCES user(id)," +
                    "FOREIGN KEY (book_id) REFERENCES book(id)," +
                    "FOREIGN KEY (created_by) REFERENCES user(id))";
            stmt.executeUpdate(sqlLoan);

            System.out.println("Tables created successfully!");

        } catch (Exception e) {
        }
    }
}
