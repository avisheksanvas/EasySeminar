/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seminarserver;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author abhey
 */
public class MySqlConnect {
    Connection conn = null;
    private static String URL = "jdbc:mysql://localhost:3306/seminarServer";
    private static String Username = "root";
    private static String Password = "uselesscoder";
    public static Connection connection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, Username, Password);
            System.out.println("Connected to Databse");
            return conn;
        } catch (Exception e) {
            System.out.println("Exception in creating database");
            return null;
        }
    }

}
