package com.sjprogramming.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    static Connection con;

    public static Connection createConnection() {
        try {
            // Load the driver (optional in newer versions but still good to include)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // ✅ Update this URL to match your actual database name
            String url = "jdbc:mysql://localhost:3306/StudentManagement?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

            // ✅ Your actual MySQL username and password
            String user = "root";
            String pass = "mYsql@123";  //  Replace with your actual password

            con = DriverManager.getConnection(url, user, pass);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}

