/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epiis.app.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author LAB306PC01
 */
public class DbConnection {
    private final String connectionString = "jdbc:mysql://localhost:3306/clinic";
    private final String user = "root";
    private final String password = "franco25";
    
    public Connection connection = null;
    
    public DbConnection() {
        this.init();
    }
    
    private void init() {
        try {
            this.connection = DriverManager.getConnection(connectionString, user, password);
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
