import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentCRUD extends JFrame {
    private JTextField nameField, oldNameField, newNameField, deleteField;
    private JButton insertButton, updateButton, deleteButton, displayButton;
    private JTable table;
    private DefaultTableModel tableModel;

    private Connection con;
    
    public StudentCRUD() {
        // Database Connection
        String url = "jdbc:mysql://localhost:3306/student";
        String username = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Database Connected");
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(this, "Database Connection Failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (ClassNotFoundException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(this, "Database Driver Not Found", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // GUI Layout
        setTitle("Student CRUD Application");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Input Fields
        nameField = new JTextField(15);
        oldNameField = new JTextField(15);
        newNameField = new JTextField(15);
        deleteField = new JTextField(15);

        insertButton = new JButton("Insert");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        displayButton = new JButton("Display");

        tableModel = new DefaultTableModel(new String[]{"ID", "Name"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Adding Components
        add(new JLabel("Student Name:"));
        add(nameField);
        add(insertButton);

        add(new JLabel("Old Name:"));
        add(oldNameField);
        add(new JLabel("New Name:"));
        add(newNameField);
        add(updateButton);

        add(new JLabel("Delete Name:"));
        add(deleteField);
        add(deleteButton);

        add(displayButton);
        add(scrollPane);

        // Button Actions
        insertButton.addActionListener(e -> insertStudent());
        updateButton.addActionListener(e -> updateStudent());
        deleteButton.addActionListener(e -> deleteStudent());
        displayButton.addActionListener(e -> displayStudents());

        setVisible(true);
    }

    private void insertStudent() {
        try {
            String name = nameField.getText();
            String sql = "INSERT INTO Student(name) VALUES(?)";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, name);
            pstm.executeUpdate();
            JOptionPane.showMessageDialog(this, "Student Added: " + name);
            nameField.setText("");
        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
    }

    private void updateStudent() {
        try {
            String oldName = oldNameField.getText();
            String newName = newNameField.getText();
            String sql = "UPDATE student SET name=? WHERE name=?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, newName);
            pstm.setString(2, oldName);
            pstm.executeUpdate();
            JOptionPane.showMessageDialog(this, "Student Name Updated: " + newName);
            oldNameField.setText("");
            newNameField.setText("");
        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
    }

    private void deleteStudent() {
        try {
            String name = deleteField.getText();
            String sql = "DELETE FROM student WHERE name=?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, name);
            pstm.executeUpdate();
            JOptionPane.showMessageDialog(this, "Deleted: " + name);
            deleteField.setText("");
        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
    }

    private void displayStudents() {
        try {
            String sql = "SELECT * FROM Student";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            tableModel.setRowCount(0); // Clear previous data
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                tableModel.addRow(new Object[]{id, name});
            }
        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new StudentCRUD();
    }
}
