package saba;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class resumeGUI extends JFrame {
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextArea educationArea;
    private JTextArea experienceArea;
    private JTextArea skillsArea;
    private JTextArea hobbiesArea;
    private JTextField dobField;

    public resumeGUI() {
        setTitle("Resume Builder");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(9, 2));

        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Phone:"));
        phoneField = new JTextField();
        panel.add(phoneField);

        panel.add(new JLabel("Education:"));
        educationArea = new JTextArea();
        JScrollPane educationScrollPane = new JScrollPane(educationArea);
        panel.add(educationScrollPane);

        panel.add(new JLabel("Experience:"));
        experienceArea = new JTextArea();
        JScrollPane experienceScrollPane = new JScrollPane(experienceArea);
        panel.add(experienceScrollPane);

        panel.add(new JLabel("Skills:"));
        skillsArea = new JTextArea();
        JScrollPane skillsScrollPane = new JScrollPane(skillsArea);
        panel.add(skillsScrollPane);

        panel.add(new JLabel("Hobbies:"));
        hobbiesArea = new JTextArea();
        JScrollPane hobbiesScrollPane = new JScrollPane(hobbiesArea);
        panel.add(hobbiesScrollPane);

        panel.add(new JLabel("Date of Birth (YYYY-MM-DD):"));
        dobField = new JTextField();
        panel.add(dobField);

        JButton saveButton = new JButton("Save Resume");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveResume();
            }
        });
        panel.add(saveButton);

        add(panel);
    }

    private void saveResume() {
        String jdbcURL = "jdbc:mysql://localhost:3307/resume_db"; 
        String username = "root";
        String password = "password";

        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String education = educationArea.getText();
        String experience = experienceArea.getText();
        String skills = skillsArea.getText();
        String hobbies = hobbiesArea.getText();
        String dob = dobField.getText();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);

            String sql = "INSERT INTO resume (name, email, phone, education, experience, skills, hobbies, dob) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, phone);
            statement.setString(4, education);
            statement.setString(5, experience);
            statement.setString(6, skills);
            statement.setString(7, hobbies);
            statement.setString(8, dob);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Resume data inserted successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to insert resume data.");
            }

            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            resumeGUI resumeBuilder = new resumeGUI();
            resumeBuilder.setVisible(true);
        });
    }
} 
