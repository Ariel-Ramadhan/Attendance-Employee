/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AttendanceEmployee;
import java.sql.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ariel
 */
public class MyConnection {
    
    private static Connection con = null;
    
    public static Connection getConnection() {
        
        try {
            if (con == null || con.isClosed()) {
                try {
                    con = DriverManager.getConnection("jdbc:sqlite:attendance.db");
                } catch (SQLException ex) {
                    Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return con;
    }
    
    public static void create_table()
    {
        ArrayList<String> sql = new ArrayList<>();

        sql.add("""
                CREATE TABLE if not exists
                user (uname varchar(30), pass varchar(30),fname varchar(30), lname vachar(30), desiqnation varchar(30));
                """);
        
        sql.add("""
                CREATE TABLE if not exists
                emp_list (srno INTEGER PRIMARY KEY AUTOINCREMENT, fname varchar(40), lname varchar(40),department varchar(40),designation varchar(40),number varchar(40),status varchar(15),address varchar(60),salary varchar(60));
                """);
        
        sql.add("""
                CREATE TABLE if not exists
                emp_abs (id INTEGER PRIMARY KEY AUTOINCREMENT, srno INTEGER, absen varchar(60), date varchar(20), time varchar(20), foreign key(srno) references emp_list(srno));
                """);
        
        //sql.add("INSERT INTO emp_abs (srno, absen, date, time) VALUES (3, 'Cuti', '12-07-2024', '20:19:58')");
        
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement())
        {
            for (String i: sql)
            {
                stmt.execute(i);
            }

        }
        
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
