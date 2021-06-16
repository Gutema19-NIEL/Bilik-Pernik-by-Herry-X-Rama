/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bilik_Pernik_Connection;

import com.mysql.jdbc.Driver;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asuna
 */
public class BK_Connection {

    
    public static Connection getBilik_Pernik_Connect() {
       Connection connect = null;
        try {
           Class.forName("com.mysql.jdbc.Driver"); /*Pemanggilan kelas pad library JDBC driver mysql*/
           connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_bilik_pernik","root", ""); /*variabel yang digunakan untuk pemanggilan driver mysql pada database server*/
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(" Mencari Koneksi " + BK_Connection.class.getName()).log(Level.SEVERE, null, e);
        }
       return connect;
       }
        
    }

