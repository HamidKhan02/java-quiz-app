package com.sjprogramming.dao;

import com.sjprogramming.db.DBConnection;
import com.sjprogramming.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class StudentDao implements StudentDaoInterface {

    @Override
    public boolean insertStudent(Student s) {
        try {
            Connection con = DBConnection.createConnection();
            String query = "INSERT INTO student_details(sname, clgname, city, percentage) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, s.getName());
            pst.setString(2, s.getClgName());
            pst.setString(3, s.getCity());
            pst.setDouble(4, s.getPercentage());
            pst.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int roll) {
        try {
            Connection con = DBConnection.createConnection();
            String query = "DELETE FROM student_details WHERE rollnum = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, roll);
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void showAllStudent() {
        try {
            Connection con = DBConnection.createConnection();
            String query = "SELECT * FROM student_details";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println("Roll Number: " + rs.getInt(1));
                System.out.println("Name: " + rs.getString(2));
                System.out.println("College Name: " + rs.getString(3));
                System.out.println("City: " + rs.getString(4));
                System.out.println("Percentage: " + rs.getDouble(5));
                System.out.println("----------------------------------");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Unused methods are removed for simplicity
}
