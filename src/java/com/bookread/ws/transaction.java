/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookread.ws;

import com.bookread.config.Models;
import com.google.gson.Gson;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author taha
 */
@WebService(serviceName = "transaction")
public class transaction {
    private String redirect_url;
    private String client_secret;
    private int amount;
    Gson gson=new Gson();

    public transaction() {
        this.redirect_url = "www.yellowkorp.com";
        this.client_secret = "clientsecret";
        this.amount=0;
    }
    
    /**
     * This is a sample web service operation
     * @param client_name
     * @param website
     * @param address
     * @return 
     */
    @WebMethod(operationName = "registerMerchant")
    public String registerMerchantWS(
            @WebParam(name = "client_name") String client_name,
            @WebParam(name = "website") String website,
            @WebParam(name = "address") String address
            ) {
        
        System.out.println("rM="+client_name+" "+website+" "+address);
        Models model=new Models();
        String result=model.registerMerchant(client_name, website, address);
        return result;
    }
    /**
     * This is a sample web service operation
     * @param client_id
     * @param client_secret
     * @param redirect_url
     * @param amount
     * @return 
     */
    @WebMethod(operationName = "createTransaction")
    public String createTransactionWS(
            @WebParam(name = "client_id") int client_id,
            @WebParam(name = "amount") Double amount,
            @WebParam(name = "client_secret") String client_secret,
            @WebParam(name = "redirect_url") String redirect_url
            ) {
        System.out.println("ci="+client_id+" "+client_secret+" "+amount+" "+redirect_url+ " ");
        Models model=new Models();
        String transaction_url=model.createTransaction(client_id, client_secret, amount, redirect_url);
        return transaction_url;
    }
    
    /**
     * This is a sample web service operation
     * @param activation_url
     * @return 
     */
    @WebMethod(operationName = "getTransaction")
    public String getTransactionWS(
            @WebParam(name = "activation_url") String activation_url
            ) {
        Models model=new Models();
        return model.getTransactionData(activation_url);
    }
    /**
     * This is a sample web service operation
     * @param trans_id
     * @param trans_details
     * @return 
     */
    @WebMethod(operationName = "completeTransaction")
    public String completeTransactionWS(
            @WebParam(name = "trans_id") int trans_id,
            @WebParam(name = "trans_details") String trans_details
            ) {
        Models model=new Models();
        return model.commitTransaction(trans_id, trans_details);
    }
}
