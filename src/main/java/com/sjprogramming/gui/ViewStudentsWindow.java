package com.sjprogramming.gui;

import com.sjprogramming.db.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewStudentsWindow extends JFrame {

    public ViewStudentsWindow() {
        setTitle("All Students");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] columnNames = {"Roll No", "Name", "College", "City", "Percentage"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        try {
            Connection con = DBConnection.createConnection();
            String query = "SELECT * FROM student_details";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Object[] row = {
                    rs.getInt("rollnum"),
                    rs.getString("sname"),
                    rs.getString("clgname"),
                    rs.getString("city"),
                    rs.getDouble("percentage")
                };
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading student data.");
            e.printStackTrace();
        }

        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }
}
