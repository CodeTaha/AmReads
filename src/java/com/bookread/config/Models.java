/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookread.config;

import com.google.gson.Gson;
import java.beans.Statement;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.math.BigInteger;
import java.security.SecureRandom;

/**
 *
 * @author taha
 */
public class Models {
    final String Base_Url="http://localhost:8084/BookRead/index.jsp?trans=";
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/azread";
    Statement stmt = null;
    Connection conn = null;
    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root123";
    Gson gson=new Gson();
    public Models() {
        try{
           //STEP 2: Register JDBC driver
           Class.forName("com.mysql.jdbc.Driver");

           //STEP 3: Open a connection
           System.out.println("Connecting to a selected database...");
           conn = DriverManager.getConnection(DB_URL, USER, PASS);
           System.out.println("Connected database successfully...");
        }catch(SQLException se){
            System.out.println("SQLException ->models->models()="+se);
        }catch(ClassNotFoundException e){
            System.out.println("ClassNotFoundException ->models->models()="+e);
        }//end try
        System.out.println("Goodbye!");
    }
    
    public String createTransaction(
            int client_id,
            String client_secret,
            int amount, 
            String redirect_url) throws NoSuchAlgorithmException{
           
      
        try {
            String hash=hashString();
            String Activation_url=Base_Url+hash;
            System.out.println(" hash="+Activation_url);
            
            String query = "insert into transactions (client_id, amount, red_url, Activation_url)"
             + " values (?, ?, ?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStmt.setInt(1, client_id);
            preparedStmt.setInt(2, amount);
            preparedStmt.setString(3, redirect_url);
            preparedStmt.setString(4, hash);
            preparedStmt.executeUpdate();
            return Activation_url;
        } catch (SQLException ex) {
            Logger.getLogger(Models.class.getName()).log(Level.SEVERE, null, ex);
            return "failed";
        }
    }
    
    private String hashString() throws NoSuchAlgorithmException{
        SecureRandom random = new SecureRandom();

        return new BigInteger(130, random).toString(32);
    }
}
