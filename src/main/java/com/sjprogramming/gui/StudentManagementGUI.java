package com.sjprogramming.gui;

import com.sjprogramming.dao.StudentDao;
import com.sjprogramming.dao.StudentDaoInterface;
import com.sjprogramming.model.Student;

import javax.swing.*;
import java.awt.*;

public class StudentManagementGUI extends JFrame {

    StudentDaoInterface dao = new StudentDao();

   public StudentManagementGUI() {
    setTitle("Student Management System");
    setSize(500, 350);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(15, 30, 15, 30);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridwidth = 2;

    JLabel heading = new JLabel("📘 Student Management System", SwingConstants.CENTER);
    heading.setFont(new Font("Arial", Font.BOLD, 20));
    gbc.gridx = 0;
    gbc.gridy = 0;
    add(heading, gbc);

    JButton addBtn = new JButton("➕ Add Student");
    JButton viewBtn = new JButton("📄 View Students");
    JButton deleteBtn = new JButton("❌ Delete Student");
    JButton exitBtn = new JButton("🚪 Exit");

    gbc.gridy = 1;
    add(addBtn, gbc);
    gbc.gridy = 2;
    add(viewBtn, gbc);
    gbc.gridy = 3;
    add(deleteBtn, gbc);
    gbc.gridy = 4;
    add(exitBtn, gbc);

    // Action listeners
    addBtn.addActionListener(e -> openAddStudentWindow());
    viewBtn.addActionListener(e -> new ViewStudentsWindow());
    deleteBtn.addActionListener(e -> new DeleteStudentWindow());
    exitBtn.addActionListener(e -> System.exit(0));

    setVisible(true);
}


    private void openAddStudentWindow() {
    JFrame addFrame = new JFrame("Add Student");
    addFrame.setSize(400, 350);
    addFrame.setLocationRelativeTo(null);
    addFrame.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    JLabel nameLabel = new JLabel("Name:");
    JTextField nameField = new JTextField(20);
    JLabel collegeLabel = new JLabel("College Name:");
    JTextField collegeField = new JTextField(20);
    JLabel cityLabel = new JLabel("City:");
    JTextField cityField = new JTextField(20);
    JLabel percentageLabel = new JLabel("Percentage:");
    JTextField percentageField = new JTextField(20);
    JButton submitBtn = new JButton("Save");

    gbc.gridx = 0; gbc.gridy = 0; addFrame.add(nameLabel, gbc);
    gbc.gridx = 1; addFrame.add(nameField, gbc);

    gbc.gridx = 0; gbc.gridy = 1; addFrame.add(collegeLabel, gbc);
    gbc.gridx = 1; addFrame.add(collegeField, gbc);

    gbc.gridx = 0; gbc.gridy = 2; addFrame.add(cityLabel, gbc);
    gbc.gridx = 1; addFrame.add(cityField, gbc);

    gbc.gridx = 0; gbc.gridy = 3; addFrame.add(percentageLabel, gbc);
    gbc.gridx = 1; addFrame.add(percentageField, gbc);

    gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
    addFrame.add(submitBtn, gbc);

    submitBtn.addActionListener(e -> {
        String name = nameField.getText();
        String clg = collegeField.getText();
        String city = cityField.getText();
        double perc;

        try {
            perc = Double.parseDouble(percentageField.getText());
            Student st = new Student(name, clg, city, perc);
            boolean inserted = dao.insertStudent(st);
            if (inserted) {
                JOptionPane.showMessageDialog(addFrame, "✅ Student Added Successfully");
                addFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(addFrame, "❌ Failed to Add Student");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(addFrame, "⚠️ Invalid percentage!");
        }
    });

    addFrame.setVisible(true);
}


    public static void main(String[] args) {
        new StudentManagementGUI();
    }
}
