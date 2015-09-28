/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ket_noi_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ket_noi {
    public final String ClassName = "com.mysql.jdbc.Driver";
    public final String Url = "jdbc:mysql://localhost:3306/oop";
    public final String User = "root";
    public final String Pass = "tanphan";
    public Connection conn;
    
    public ket_noi(){
        
    }
    public void connect_to_DB(){
        try {
            Class.forName(ClassName);
            conn = DriverManager.getConnection(Url,User, Pass);
            System.out.println("Kết nối thành công!");
        } catch (ClassNotFoundException ex) {
            System.out.println("Không tìm thấy class!");
        } catch (SQLException ex) {
            System.out.println("Lỗi kết nối!");
        }
    }
}
