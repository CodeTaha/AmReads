/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookread.rs;

import com.bookread.config.Models;
import java.security.NoSuchAlgorithmException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author taha
 */
@Path("transaction")
public class TransactionResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TransactionResource
     */
    public TransactionResource() {
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
    public String createTransaction(@PathParam("client_id") int client_id,
            @DefaultValue("0") @QueryParam("amount") int amount,
            @DefaultValue("clientsecret") @QueryParam("client_secret") String client_secret,
            @DefaultValue("www.yellowkorp.com") @QueryParam("redirect_url") String redirect_url) throws NoSuchAlgorithmException {
        
        System.out.println("ci="+client_id+" "+client_secret+" "+amount+" "+redirect_url+ " ");
        Models model=new Models();
        String transaction_url=model.createTransaction(client_id, client_secret, amount, redirect_url);
        return "{\"transaction_url\":\""+transaction_url+"\"}";
    }
    
    /**
     * Retrieves representation of an instance of com.bookread.rs.TransactionResource
     * @param activation_url
     * @param client_id
     * @param client_secret
     * @param amount
     * @param redirect_url
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
    public String completeTransaction(@PathParam("trans_id") int trans_id,
            @DefaultValue("{\"trans_details\":0}") @QueryParam("trans_details") String trans_details) throws NoSuchAlgorithmException {
        System.out.println("trans_details"+trans_details);
        Models model=new Models();
        String transaction_url=model.commitTransaction(trans_id);
        System.out.println("trans_url"+transaction_url);
        return transaction_url;
    }
    /**
     * PUT method for updating or creating an instance of TransactionResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
