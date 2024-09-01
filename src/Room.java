import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Room {
    private long id;
    private final String name;

    public Room(String name) {
        this.name = name;
        this.id = -1; // Indicates the room has not been added yet
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addRoom() {
        String url = "jdbc:mysql://127.0.0.1:3306/libirary";
        String user = "root";
        String password = "1234";

        String insertRoomSQL = "INSERT INTO room (name) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(insertRoomSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, name);
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    this.id = generatedKeys.getLong(1);
                }
            }

        } catch (SQLException ex) {
            System.err.println("SQL error while adding room: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Unexpected error while adding room: " + ex.getMessage());
        }
    }

    public void addBookToRoom(long bookId, long roomid) {
        String url = "jdbc:mysql://127.0.0.1:3306/libirary";
        String user = "root";
        String password = "1234";

        String updateBookSQL = "UPDATE book SET room_id = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(updateBookSQL)) {

            stmt.setLong(1, roomid);
            stmt.setLong(2, bookId);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("SQL error while adding book to room: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Unexpected error while adding book to room: " + ex.getMessage());
        }
    }
}
