/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookread.rs;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import javax.ws.rs.PathParam;
import org.json.JSONObject;
import org.json.XML;

//import net.sf.json.JSON
/**
 * REST Web Service
 *
 * @author taha
 */
@Path("amazonClient")
public class AmazonClientResource implements configAWS{
    
    /*
     * Set up the signed requests helper.
     */
  Gson gson = new Gson();
  private SignedRequestsHelper helper;
  String requestUrl = null;
  @Context
  private UriInfo context;

  /**
   * Creates a new instance of AmazonClientResource
   */
  public AmazonClientResource() {
     try {
          helper = SignedRequestsHelper.getInstance(ENDPOINT, AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
            System.out.println("Helper created successfully");
     } catch (IllegalArgumentException | UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException e) {
       Logger.getLogger(AmazonClientResource.class.getName()).log(Level.SEVERE, null, e);
     }
            

        
  }
  /**
   * Retrieves representation of an instance of com.bookread.rs.AmazonClientResource
   * @return an instance of java.lang.String
   */
  @POST
  @Produces("application/json")
  @Path("/itemSearch")
  public String itemSearch(
          @DefaultValue("J.K Rowling") @QueryParam("keywords") String keywords,
          @DefaultValue("price") @QueryParam("SortBy") String SortBy
          
  ) {
    System.out.println("Keyword"+keywords);
    //TODO return proper representation object
    try{
      Map<String, String> params = new HashMap<String, String>();

      params.put("Service", "AWSECommerceService");
      params.put("Operation", "ItemSearch");
      params.put("AWSAccessKeyId", AWS_ACCESS_KEY_ID);
      params.put("AssociateTag", AssociateTag);
      params.put("SearchIndex", "Books");
      params.put("Keywords", keywords);
      params.put("ResponseGroup", "Images,ItemAttributes, OfferSummary");
      params.put("Sort", SortBy);

      requestUrl = helper.sign(params);

      System.out.println("Signed URL: \"" + requestUrl + "\"");
      return sendGet(requestUrl);
    } catch (Exception e){
      return "Failed";
    }
    
  }
  /**
   * Retrieves representation of an instance of com.bookread.rs.AmazonClientResource
   * @param isbn
   * @return an instance of java.lang.String
   */
  @GET
  @Produces("application/json")
  @Path("/getreview/{isbn}")
  public String getreview(@PathParam("isbn") String isbn) {
    String url="http://www.goodreads.com/book/isbn?format=json&isbn="+isbn+"&key="+GoodReadKey;
    try{
    URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
    if(responseCode!=200){
      return "{\"error\":\"No Reviews\"}";
    }
    StringBuffer response;
    try (BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()))) {
      String inputLine;
      response = new StringBuffer();
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
    }
    String resp = response.toString();
    //System.out.println("GR json"+resp);
    return resp;
    } catch(Exception e){
      System.out.println(e);
    return "{\"error\":\"No Reviews\"}";
    }
  }
   /**
   * Retrieves representation of an instance of com.bookread.rs.AmazonClientResource
   * @param isbn
   * @param amount
   * @param details
   * @return an instance of java.lang.String
   * @throws java.net.MalformedURLException
   */
  @GET
  @Produces("application/json")
  @Path("/getBuy")
  public String getBuy(
          @DefaultValue("0") @QueryParam("isbn") String isbn,
          @DefaultValue("0") @QueryParam("amount") Double amount,
          @DefaultValue("No Details") @QueryParam("details") String details
  ) throws MalformedURLException {
    try {
      System.out.println(isbn+" "+ amount);
      String redirect_url= new AmModel().createPurchase(amount, isbn, details);
      String url="http://localhost:8084/AmRead/webresources/transaction/createTransaction/"+AmReads_client_id+"?client_secret="+AmReads_client_secret+"&amount="+amount+"&redirect_url="+redirect_url;
      URL obj = new URL(url);
      HttpURLConnection con = (HttpURLConnection) obj.openConnection();
      
      // optional default is GET
      con.setRequestMethod("POST");
      
      //add request header
      con.setRequestProperty("User-Agent", "Mozilla/5.0");
      
      int responseCode = con.getResponseCode();
      System.out.println("\nSending 'GET' request to URL : " + url);
      System.out.println("Response Code : " + responseCode);
      if(responseCode!=200){
        return "{\"error\":\"No Reviews\"}";
      }
      StringBuffer response;
      try (BufferedReader in = new BufferedReader(
              new InputStreamReader(con.getInputStream()))) {
        String inputLine;
        response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
          response.append(inputLine);
        }
      }
      System.out.println("Successful"+response.toString());
      return response.toString();
    } catch (IOException ex) {
      Logger.getLogger(AmazonClientResource.class.getName()).log(Level.SEVERE, null, ex);
      return "{\"error\":\"Could Not create Transaction\"}";
    }
  };
  
   /**
     * Retrieves representation of an instance of com.bookread.rs.TransactionResource
     * @param activation_url
     * @return an instance of java.lang.String
     * @throws java.security.NoSuchAlgorithmException
     */
    @GET
    @Path("/completePurchase/{activation_url}")
    @Produces("application/json")
    public String getTransaction(@PathParam("activation_url") String activation_url) throws NoSuchAlgorithmException {
        
        System.out.println("ci="+activation_url);
        AmModel ammodel = new AmModel();
        return ammodel.completePurchase(activation_url);
        //return "{\"transaction_url\":\""+activation_url+"\"}";
    }
  /**
   * Retrieves representation of an instance of com.bookread.rs.AmazonClientResource
   * @return an instance of java.lang.String
   */
  @GET
  @Produces("application/json")
  public String getJson() {
    //TODO return proper representation object
    return "";
  }
  

  /**
   * PUT method for updating or creating an instance of AmazonClientResource
   * @param content representation for the resource
   */
  @PUT
  @Consumes("application/json")
  public void putJson(String content) {
  }
  /* 
    For sendng remote GET requests to Amazon
  */
  public String sendGet(String url) throws Exception {

		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
    
    StringBuffer response;
    try (BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()))) {
      String inputLine;
      response = new StringBuffer();
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
    }

		//print result
    String respXML = response.toString();
		//System.out.println("XML op"+respXML);
    try {
      JSONObject JSONObj = XML.toJSONObject(respXML);
      
      System.out.println(JSONObj.length());
      String json = JSONObj.toString(4);
      return json;
    } catch (Exception e){
      System.out.println("Not that great"+e);
      return "Failed";
    }
	}

}
