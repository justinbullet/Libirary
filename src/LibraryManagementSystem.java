import java.awt.*;
import javax.swing.*;

public class LibraryManagementSystem extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public LibraryManagementSystem() {
        setTitle("Library Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Menu bar
        JMenuBar menuBar = new JMenuBar();

        JMenu userMenu = new JMenu("User");
        JMenuItem addUserMenuItem = new JMenuItem("Add User");
        JMenuItem deleteUserMenuItem = new JMenuItem("Delete User");
        userMenu.add(addUserMenuItem);
        userMenu.add(deleteUserMenuItem);
        menuBar.add(userMenu);

        JMenu roomMenu = new JMenu("Room");
        JMenuItem addRoomMenuItem = new JMenuItem("Add Room");
        JMenuItem addBookToRoomMenuItem = new JMenuItem("Add Book to Room");
        roomMenu.add(addRoomMenuItem);
        roomMenu.add(addBookToRoomMenuItem);
        menuBar.add(roomMenu);

        JMenu bookMenu = new JMenu("Book");
        JMenuItem addBookMenuItem = new JMenuItem("Add Book");
        JMenuItem deleteBookMenuItem = new JMenuItem("Delete Book");
        JMenuItem borrowBookMenuItem = new JMenuItem("Borrow Book");
        JMenuItem searchBookMenuItem = new JMenuItem("Search Book");
        bookMenu.add(addBookMenuItem);
        bookMenu.add(deleteBookMenuItem);
        bookMenu.add(borrowBookMenuItem);
        bookMenu.add(searchBookMenuItem);
        menuBar.add(bookMenu);

        setJMenuBar(menuBar);

        // Main panel with CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // User panels
        mainPanel.add(createAddUserPanel(), "Add User");
        mainPanel.add(createDeleteUserPanel(), "Delete User");

        // Room panels
        mainPanel.add(createAddRoomPanel(), "Add Room");
        mainPanel.add(createAddBookToRoomPanel(), "Add Book to Room");

        // Book panels
        mainPanel.add(createAddBookPanel(), "Add Book");
        mainPanel.add(createDeleteBookPanel(), "Delete Book");
        mainPanel.add(createBorrowBookPanel(), "Borrow Book");
        mainPanel.add(createSearchBookPanel(), "Search Book");

        add(mainPanel);

        // Event Listeners
        addUserMenuItem.addActionListener(e -> cardLayout.show(mainPanel, "Add User"));
        deleteUserMenuItem.addActionListener(e -> cardLayout.show(mainPanel, "Delete User"));
        addRoomMenuItem.addActionListener(e -> cardLayout.show(mainPanel, "Add Room"));
        addBookToRoomMenuItem.addActionListener(e -> cardLayout.show(mainPanel, "Add Book to Room"));
        addBookMenuItem.addActionListener(e -> cardLayout.show(mainPanel, "Add Book"));
        deleteBookMenuItem.addActionListener(e -> cardLayout.show(mainPanel, "Delete Book"));
        borrowBookMenuItem.addActionListener(e -> cardLayout.show(mainPanel, "Borrow Book"));
        searchBookMenuItem.addActionListener(e -> cardLayout.show(mainPanel, "Search Book"));
    }

    private JPanel createAddUserPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JTextField userNameField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JButton addUserButton = new JButton("Add User");

        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; panel.add(userNameField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(emailField, gbc);
        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; panel.add(passwordField, gbc);
        gbc.gridx = 1; gbc.gridy = 3; panel.add(addUserButton, gbc);

        // Add Action Listener for the button
        addUserButton.addActionListener(e -> {
            // Implement your addUser function here
            JOptionPane.showMessageDialog(this, "User added successfully!");
        });

        return panel;
    }

    private JPanel createDeleteUserPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JTextField userIdField = new JTextField(20);
        JButton deleteUserButton = new JButton("Delete User");

        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("User ID:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; panel.add(userIdField, gbc);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(deleteUserButton, gbc);

        // Add Action Listener for the button
        deleteUserButton.addActionListener(e -> {
            // Implement your deleteUser function here
            JOptionPane.showMessageDialog(this, "User deleted successfully!");
        });

        return panel;
    }

    private JPanel createAddRoomPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JTextField roomNameField = new JTextField(20);
        JButton addRoomButton = new JButton("Add Room");

        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Room Name:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; panel.add(roomNameField, gbc);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(addRoomButton, gbc);

        // Add Action Listener for the button
        addRoomButton.addActionListener(e -> {
            // Implement your addRoom function here
            JOptionPane.showMessageDialog(this, "Room added successfully!");
        });

        return panel;
    }

    private JPanel createAddBookToRoomPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JTextField bookIdField = new JTextField(20);
        JTextField roomIdField = new JTextField(20);
        JButton addBookToRoomButton = new JButton("Add Book to Room");

        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Book ID:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; panel.add(bookIdField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Room ID:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(roomIdField, gbc);
        gbc.gridx = 1; gbc.gridy = 2; panel.add(addBookToRoomButton, gbc);

        // Add Action Listener for the button
        addBookToRoomButton.addActionListener(e -> {
            // Implement your addBookToRoom function here
            JOptionPane.showMessageDialog(this, "Book added to room successfully!");
        });

        return panel;
    }

    private JPanel createAddBookPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JTextField bookTitleField = new JTextField(20);
        JButton addBookButton = new JButton("Add Book");

        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Book Title:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; panel.add(bookTitleField, gbc);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(addBookButton, gbc);

        // Add Action Listener for the button
        addBookButton.addActionListener(e -> {
            String title = bookTitleField.getText();
            Book book = new Book(title);
            book.addBook();
            // Implement your addBook function here
            JOptionPane.showMessageDialog(this, "Book added successfully!");
        });

        return panel;
    }

    private JPanel createDeleteBookPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JTextField bookIdField = new JTextField(20);
        JButton deleteBookButton = new JButton("Delete Book");

        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Book ID:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; panel.add(bookIdField, gbc);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(deleteBookButton, gbc);

        // Add Action Listener for the button
        deleteBookButton.addActionListener(e -> {
            // Implement your deleteBook function here
            JOptionPane.showMessageDialog(this, "Book deleted successfully!");
        });

        return panel;
    }

    private JPanel createBorrowBookPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JTextField userIdField = new JTextField(20);
        JTextField bookIdField = new JTextField(20);
        JButton borrowBookButton = new JButton("Borrow Book");

        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("User ID:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; panel.add(userIdField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Book ID:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(bookIdField, gbc);
        gbc.gridx = 1; gbc.gridy = 2; panel.add(borrowBookButton, gbc);

        // Add Action Listener for the button
        borrowBookButton.addActionListener(e -> {
            // Implement your borrowBook function here
            JOptionPane.showMessageDialog(this, "Book borrowed successfully!");
        });

        return panel;
    }

    private JPanel createSearchBookPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JTextField bookTitleField = new JTextField(20);
        JButton searchBookButton = new JButton("Search Book");

        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Book Title:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; panel.add(bookTitleField, gbc);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(searchBookButton, gbc);

        // Add Action Listener for the button
        searchBookButton.addActionListener(e -> {
            // Implement your searchBook function here
            JOptionPane.showMessageDialog(this, "Search completed successfully!");
        });

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LibraryManagementSystem().setVisible(true);
        });
    }
}
