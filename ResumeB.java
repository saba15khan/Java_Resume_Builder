package saba;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ResumeB extends JFrame {
    private JTextField nameField;
    private JTextField emailField;
    private JTextArea addressArea;
    private JTextArea educationArea;
    private JTextArea experienceArea;
    private JTextArea skillsArea;
    private JTextArea achievementsArea;
    private JTextArea certificatesArea;

    public ResumeB() {
        setTitle("Colorful Resume Builder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(9, 2, 10, 10));

        mainPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        mainPanel.add(nameField);

        mainPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        mainPanel.add(emailField);

        mainPanel.add(new JLabel("Address:"));
        addressArea = new JTextArea(3, 20);
        mainPanel.add(new JScrollPane(addressArea));

        mainPanel.add(new JLabel("Education:"));
        educationArea = new JTextArea(5, 20);
        mainPanel.add(new JScrollPane(educationArea));

        mainPanel.add(new JLabel("Experience:"));
        experienceArea = new JTextArea(5, 20);
        mainPanel.add(new JScrollPane(experienceArea));

        mainPanel.add(new JLabel("Skills:"));
        skillsArea = new JTextArea(5, 20);
        mainPanel.add(new JScrollPane(skillsArea));

        mainPanel.add(new JLabel("Achievements:"));
        achievementsArea = new JTextArea(3, 20);
        mainPanel.add(new JScrollPane(achievementsArea));

        mainPanel.add(new JLabel("Certificates:"));
        certificatesArea = new JTextArea(3, 20);
        mainPanel.add(new JScrollPane(certificatesArea));

        JButton generateButton = new JButton("Generate Resume");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateResume();
            }
        });
        mainPanel.add(generateButton);

        JButton saveButton = new JButton("Save Resume");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveResume();
            }
        });
        mainPanel.add(saveButton);

        JButton loadButton = new JButton("Load Resume");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadResume();
            }
        });
        mainPanel.add(loadButton);

        JButton clearButton = new JButton("Clear Fields");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        mainPanel.add(clearButton);

        add(mainPanel);
    }

    private void generateResume() {
        String name = nameField.getText();
        String email = emailField.getText();
        String address = addressArea.getText();
        String education = educationArea.getText();
        String experience = experienceArea.getText();
        String skills = skillsArea.getText();
        String achievements = achievementsArea.getText();
        String certificates = certificatesArea.getText();

        // Generate the resume using the provided data
        String resume = "<html><head><style>";
        resume += "body { font-family: Arial, sans-serif; }";
        resume += "h1 { color: #007acc; }";
        resume += "h2 { color: #333; }";
        resume += "p { margin: 0; }";
        resume += "</style></head><body>";
        resume += "<h1>" + name + "</h1>";
        resume += "<p><strong>Email:</strong> " + email + "</p>";
        resume += "<p><strong>Address:</strong><br>" + address + "</p>";
        resume += "<h2>Education</h2>";
        resume += "<p>" + education + "</p>";
        resume += "<h2>Experience</h2>";
        resume += "<p>" + experience + "</p>";
        resume += "<h2>Skills</h2>";
        resume += "<p>" + skills + "</p>";
        resume += "<h2>Achievements</h2>";
        resume += "<p>" + achievements + "</p>";
        resume += "<h2>Certificates</h2>";
        resume += "<p>" + certificates + "</p>";
        resume += "</body></html>";

        // Display the generated resume in a new window
        JFrame resumeWindow = new JFrame("Generated Resume");
        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setText(resume);
        textPane.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textPane);
        resumeWindow.add(scrollPane);
        resumeWindow.setSize(800, 600);
        resumeWindow.setVisible(true);
    }


    private void saveResume() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            try (PrintWriter writer = new PrintWriter(fileChooser.getSelectedFile())) {
                writer.println("Name: " + nameField.getText());
                writer.println("Email: " + emailField.getText());
                writer.println("Address:");
                writer.println(addressArea.getText());
                writer.println("Education:");
                writer.println(educationArea.getText());
                writer.println("Experience:");
                writer.println(experienceArea.getText());
                writer.println("Skills:");
                writer.println(skillsArea.getText());
                writer.println("Achievements:");
                writer.println(achievementsArea.getText());
                writer.println("Certificates:");
                writer.println(certificatesArea.getText());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving file.", "File Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void loadResume() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()))) {
                StringBuilder resumeText = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    resumeText.append(line).append("\n");
                }
                // Display the loaded resume in a dialog
                JOptionPane.showMessageDialog(this, resumeText.toString(), "Loaded Resume", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error loading file.", "File Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void clearFields() {
        nameField.setText("");
        emailField.setText("");
        addressArea.setText("");
        educationArea.setText("");
        experienceArea.setText("");
        skillsArea.setText("");
        achievementsArea.setText("");
        certificatesArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ResumeB().setVisible(true);
            }
        });
    }
}
