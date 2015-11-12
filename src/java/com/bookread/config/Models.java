/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookread.config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
import java.sql.ResultSet;
import java.util.ArrayList;

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
            String redirect_url){
           
      
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
    
    private String hashString(){
        try {
            SecureRandom random = new SecureRandom();
            return new BigInteger(130, random).toString(32);
        } catch(Exception e){
            return "testurl";
        }
        
    }

    public String getTransactionData(String activation_url) {
        try {
            System.out.println(" act_url="+activation_url);
            
            String query = "SELECT * FROM transactions where activation_url=? and flag=0 limit 1";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, activation_url);
            ResultSet rs=preparedStmt.executeQuery();
            if (rs.next())
            {
                JsonObject res = new JsonObject();
                res.addProperty("trans_id", rs.getInt("Transaction_id"));
                res.addProperty("client_id", rs.getInt("client_id"));
                res.addProperty("flag", rs.getInt("flag"));
                //res.addProperty("flag", 1);
                res.addProperty("amount", rs.getInt("amount"));
                //res.addProperty("redirect_url", rs.getString("red_url"));
                System.out.println("Search"+gson.toJson(res));
                
                return gson.toJson(res);
            }
            System.out.println("Flag or transaction not found");
            return "{\"flag\":1}";
        } catch (SQLException ex) {
            Logger.getLogger(Models.class.getName()).log(Level.SEVERE, null, ex);
            return "{\"flag\":1}";
        }
    }

    public String commitTransaction(int trans_id) {
        try {
            System.out.println(" trans_id="+trans_id);
    //UPDATE transactions SET flag=1 WHERE Transaction_id=6;        
            String query = "UPDATE transactions SET flag=1 WHERE Transaction_id=?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, trans_id);
            int updated=preparedStmt.executeUpdate();
            System.out.println("updated="+updated);
            query = "SELECT * FROM transactions where Transaction_id=?";
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, trans_id);
            
            ResultSet rs=preparedStmt.executeQuery();
            if (rs.next())
            {
                JsonObject res = new JsonObject();
                res.addProperty("trans_id", rs.getInt("Transaction_id"));
                res.addProperty("client_id", rs.getInt("client_id"));
                res.addProperty("flag", rs.getInt("flag"));
                //res.addProperty("flag", 1);
                res.addProperty("amount", rs.getInt("amount"));
                res.addProperty("redirect_url", rs.getString("red_url"));
                System.out.println("Search"+gson.toJson(res));
                
                return gson.toJson(res);
            }
            System.out.println("Flag or transaction not found");
            return "{\"flag\":0}";
        } catch (SQLException ex) {
            Logger.getLogger(Models.class.getName()).log(Level.SEVERE, null, ex);
            return "{\"flag\":0}";
        }
    }
}
