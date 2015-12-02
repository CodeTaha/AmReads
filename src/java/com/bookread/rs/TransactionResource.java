/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.bookread.rs;

import com.bookread.config.Models;
import com.google.gson.Gson;
import fi.aalto.t_75_5300.bank.TransactionException_Exception;
import fi.aalto.t_75_5300.bank.TransactionResult;
import fi.aalto.t_75_5300.bank.VisaCard;
import fi.aalto.t_75_5300.bank.VisaTransaction;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author taha
 */
@Path("transaction")
@Consumes({"text/plain,text/html,application/x-www-form-urlencoded"})
public class TransactionResource {
  Gson gson= new Gson();
  @Context
  private UriInfo context;
  
  /**
   * Creates a new instance of TransactionResource
   */
  public TransactionResource() {
  }
  /**
   * Retrieves representation of an instance of com.bookread.rs.GenericResource
   * @return an instance of java.lang.String
   */
  @GET
  @Produces("application/xml")
  public String getXml() {
    //TODO return proper representation object
    throw new UnsupportedOperationException();
  }
  /**
   * Retrieves representation of an instance of com.bookread.rs.TransactionResource
   * @param client_name
   * @param website
   * @param address
   * @return an instance of java.lang.String
   * @throws java.security.NoSuchAlgorithmException
   */
  @POST
  @Path("/registerMerchant")
  @Produces("application/json")
  @Consumes("application/x-www-form-urlencoded")
  public String registerMerchant(
          @DefaultValue("client_name") @QueryParam("client_name") String client_name,
          @DefaultValue("mosh") @QueryParam("website") String website,
          @DefaultValue("address") @QueryParam("address") String address) throws NoSuchAlgorithmException {
    
    System.out.println("rM="+client_name+" "+website+" "+address);
    Models model=new Models();
    String result=model.registerMerchant(client_name, website, address);
    return result;//result;
  }
  
  /**
   * Retrieves representation of an instance of com.bookread.rs.TransactionResource
   * @param client_id
   * @param client_secret
   * @param amount
   * @param redirect_url
   * @return an instance of java.lang.String
   * @throws java.security.NoSuchAlgorithmException
   */
  @POST
  @Path("/createTransaction/{client_id}")
  @Produces("application/json")
  @Consumes("application/x-www-form-urlencoded")
  public String createTransaction(@PathParam("client_id") int client_id,
          @DefaultValue("0") @QueryParam("amount") Double amount,
          @DefaultValue("clientsecret") @QueryParam("client_secret") String client_secret,
          @DefaultValue("www.yellowkorp.com") @QueryParam("redirect_url") String redirect_url) throws NoSuchAlgorithmException {
    System.out.println("ci="+client_id+" "+client_secret+" "+amount+" "+redirect_url+ " ");
    Models model=new Models();
    String transaction_url=model.createTransaction(client_id, client_secret, amount, redirect_url);
    return transaction_url;
  }
  
  /**
   * Retrieves representation of an instance of com.bookread.rs.TransactionResource
   * @param activation_url
   * @return an instance of java.lang.String
   * @throws java.security.NoSuchAlgorithmException
   */
  @GET
  @Path("/getTransaction/{activation_url}")
  @Produces("application/json")
  public String getTransaction(@PathParam("activation_url") String activation_url) throws NoSuchAlgorithmException {
    
    System.out.println("ci="+activation_url);
    Models model=new Models();
    return model.getTransactionData(activation_url);
    //return "{\"transaction_url\":\""+activation_url+"\"}";
  }
  
  /**
   * Retrieves representation of an instance of com.bookread.rs.TransactionResource
   * @param trans_id
   * @param trans_details
   * @return an instance of java.lang.String
   * @throws java.security.NoSuchAlgorithmException
   */
  @POST
  @Path("/completeTransaction/{trans_id}")
  @Produces("application/json")
  @Consumes("application/x-www-form-urlencoded")
  public String completeTransaction(@PathParam("trans_id") int trans_id,
          @DefaultValue("{\"trans_details\":0}") @QueryParam("trans_details") String trans_details) throws NoSuchAlgorithmException {
    System.out.println("trans_details"+trans_details);
    Models model=new Models();
    String transaction_url=model.commitTransaction(trans_id,trans_details);
    System.out.println("trans_url"+transaction_url);
    return transaction_url;
  }
  /**
   * Retrieves representation of an instance of com.bookread.rs.AmazonClientResource
   * @param name
   * @return an instance of java.lang.String
   */
//  @POST
//  @Path("/getBuy3")
//  @Consumes("application/x-www-form-urlencoded")
//  @Produces("application/json")
//  public String getBuy3(
//          @DefaultValue("defname") @FormParam("name") String name,
//          @DefaultValue("defname") @FormParam("cardNumber") String cardNumber,
//          @DefaultValue("defname") @FormParam("csv") String csv,
//          @DefaultValue("defname") @FormParam("month") int month,
//          @DefaultValue("defname") @FormParam("year") int year
//          //@DefaultValue("defname") @FormParam("amount") int amount
//  ) {
//    System.out.println("Implementing external Bank"+name+" "+cardNumber+" "+csv+" "+month+" "+year+" ");
////    VisaCard vcard = new VisaCard();
////    vcard.setCsv("");
////    vcard.setNumber("");
////    vcard.setOwner("");
////    vcard.setValidMonth(6);
////    vcard.setValidYear(1992);
////    VisaTransaction bank2= new VisaTransaction();
////    bank2.setAmountInCents(100);
////    bank2.setCard(vcard);
////    bank2.setTargetIBAN("FR14 2004 1010 0505 0001 3M02 606");
////    bank2.setTransactionMessage("Transaction Successful of AmReads");
////    try {
////      TransactionResult tr=makeVisaTransaction(bank2);
////      return gson.toJson(tr);
////    } catch (TransactionException_Exception ex) {
////      Logger.getLogger(AmazonClientResource.class.getName()).log(Level.SEVERE, null, ex);
////      System.out.println("Bank2 error="+ex);
////      return "{\"error\":\""+ex+"\"}";
////    }
//    return "";
//  }
  /**
   * Retrieves representation of an instance of com.bookread.rs.TransactionResource
   * @param trans_id
   * @param trans_details
   * @return an instance of java.lang.String
   * @throws java.security.NoSuchAlgorithmException
   */
  @POST
  @Path("/getBuy2")
  @Produces("application/json")
  @Consumes("application/x-www-form-urlencoded")
  public String bankTransaction(
          @DefaultValue("defname") @FormParam("name") String name,
          @DefaultValue("defname") @FormParam("cardNumber") String cardNumber,
          @DefaultValue("defname") @FormParam("csv") String csv,
          @DefaultValue("12") @FormParam("month") int month,
          @DefaultValue("2016") @FormParam("year") int year,
          @DefaultValue("0") @FormParam("amount") int amount
  ) throws NoSuchAlgorithmException {
        System.out.println("Implementing external Bank"+name+" "+cardNumber+" "+csv+" "+month+" "+year+" "+amount);
    VisaCard vcard = new VisaCard();
    vcard.setCsv(csv);
    vcard.setNumber(cardNumber);
    vcard.setOwner(name);
    vcard.setValidMonth(month);
    vcard.setValidYear(year);
    VisaTransaction bank2= new VisaTransaction();
    bank2.setAmountInCents(100);
    bank2.setCard(vcard);
    bank2.setTargetIBAN("FR14 2004 1010 0505 0001 3M02 606");
    bank2.setTransactionMessage("Transaction Successful of AmReads");
    try {
      TransactionResult tr=makeVisaTransaction(bank2);
      return gson.toJson(tr);
    } catch (TransactionException_Exception ex) {
      Logger.getLogger(AmazonClientResource.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("Bank2 error="+ex);
      return "{\"error\":\""+ex+"\"}";
    }
  }
  private static TransactionResult makeVisaTransaction(fi.aalto.t_75_5300.bank.VisaTransaction arg0) throws TransactionException_Exception {
    fi.aalto.t_75_5300.bank.TransactionService service = new fi.aalto.t_75_5300.bank.TransactionService();
    fi.aalto.t_75_5300.bank.Transactions port = service.getTransactionsPort();
    return port.makeVisaTransaction(arg0);
  }
}
