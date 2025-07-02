package com.sjprogramming.gui;

import com.sjprogramming.dao.StudentDao;
import com.sjprogramming.dao.StudentDaoInterface;

import javax.swing.*;
import java.awt.*;

public class DeleteStudentWindow extends JFrame {

    StudentDaoInterface dao = new StudentDao();

    public DeleteStudentWindow() {
        setTitle("Delete Student");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel label = new JLabel("Enter Roll Number:");
        JTextField rollField = new JTextField(15);
        JButton deleteBtn = new JButton("Delete");

        gbc.gridx = 0; gbc.gridy = 0; add(label, gbc);
        gbc.gridx = 1; add(rollField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        add(deleteBtn, gbc);

        deleteBtn.addActionListener(e -> {
            try {
                int roll = Integer.parseInt(rollField.getText());
                boolean deleted = dao.delete(roll);
                if (deleted) {
                    JOptionPane.showMessageDialog(this, "✅ Student Deleted");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "❌ Roll number not found");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "⚠️ Invalid Roll Number!");
            }
        });

        setVisible(true);
    }
}
