/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookread.rs;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.ResultSet;
/**
 *
 * @author taha
 */
public class AmModel {
  final String Base_Url="http://localhost:8084/AmRead/complete.jsp?trans=";
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/azread";
    
    static final String USER = "root";
    static final String PASS = "root123";
    Statement stmt = null;
    Connection conn = null;
    //  Database credentials
    Gson gson=new Gson();
    
    public AmModel(){
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
    
    public String createPurchase(Double amount, String isbn, String details){
      try {
        String hash=hashString();
        String redirect_url=Base_Url+hash;
        String query = "insert into purchase_tbl (isbn,amount,details,redirect_url)"
                + " values (?, ?, ?, ?)";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, isbn);
        preparedStmt.setDouble(2, amount);
        preparedStmt.setString(3, details);
        preparedStmt.setString(4, hash);
        preparedStmt.executeUpdate();
        return redirect_url;
      } catch (SQLException ex) {
        Logger.getLogger(AmModel.class.getName()).log(Level.SEVERE, null, ex);
        return "{\"error\":\""+ex+"\"}";
      }
    }
    public String completePurchase(String activation_url){
    try {
      String query = "UPDATE purchase_tbl SET flag=1 WHERE redirect_url=?";
      PreparedStatement preparedStmt = conn.prepareStatement(query);
      preparedStmt.setString(1, activation_url);
      int updated=preparedStmt.executeUpdate();
      System.out.println("updated="+updated);
      return "{\"flag\":0}";
    } catch (SQLException ex) {
      Logger.getLogger(AmModel.class.getName()).log(Level.SEVERE, null, ex);
      return "{\"flag\":1}";
    }
    }

    private String hashString() {
      try {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
      } catch(Exception e){
        return "testurl";
      }
    }
}
