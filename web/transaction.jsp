<%-- 
    Document   : index
    Created on : Nov 11, 2015, 3:39:47 AM
    Author     : taha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BookRead Transaction Gateway</title>
        <link rel="stylesheet" type="text/css" href="scripts/vendor/bootstrap/dist/css/bootstrap.css">
    </head>
    <body>
        <%
        String trans=request.getParameter("trans");
        if (trans == null) {%>
        <h1>Sorry Broken Link!</h1>
        <h3>Transaction cannot be processed</h3>
        <%
        } else {
        %>
        <h1>Welcome to AmReads Transaction Gateway!</h1>
        <p><%= trans %></p>
        <div id="main" class="container">
          </br>
          <div id="mesg"></div>
          <div class="row">
            
            <button id="paylocal"  class='btn btn-primary col-md-1 col-md-offset-3' role='button' onclick='pay()'>AmPay</button>
            <div class="col-md-6">
              <div class="form-horizontal">
                <div class="form-group">
                  <label for="name" class="col-sm-2 control-label">Owner name</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="name" placeholder="Owner Name" value="" required/>
                  </div>
                </div>
                <div class="form-group">
                  <label for="cardNumber" class="col-sm-2 control-label">Card Number</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="cardNumber" placeholder="4111111111111111" value="4111111111111111">
                  </div>
                </div>
                <div class="form-group">
                  <label for="csv" class="col-sm-2 control-label">CSV</label>
                  <div class="col-sm-2">
                    <input type="password" class="form-control" id="csv" placeholder="csv">
                  </div>
                </div>
                <div class="form-group">
                  <label for="month" class="col-sm-2 control-label">Exp Month</label>
                  <div class="col-sm-3">
                    <input type="number" class="form-control" id="month" placeholder="Expiration Month">
                  </div>
                </div>
                <div class="form-group">
                  <label for="year" class="col-sm-2 control-label">Exp Year</label>
                  <div class="col-sm-3">
                    <input type="number" class="form-control" id="year" placeholder="Expiration Month">
                  </div>
                </div>
                <div class="form-group">
                  <div class="col-sm-offset-2 col-sm-10">
                    <button id="pay2" onclick="pay2()" class="btn btn-default">Pay through Card</button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <script src="js/jquery.min.js"></script>
        <script src="scripts/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
        <script>
            var act_url="<%= trans %>";
            console.log(act_url);
            $("paylocal").hide();
            var data;
            $(document).ready(function() {
                $.ajax({
                    url: "webresources/transaction/getTransaction/"+act_url,
                    type: "GET", //send it through get method
                    success: function(response) {
                        data=response;
                        if(data.flag==1) {
                            $("#main").append("<h3>Sorry this transaction does not exist, please contact your merchant</h3>");
                        } else {
                            $("#mesg").append("Press AmPay to pay through AmReads $"+data.amount+" to "+data.client_id+"-"+data.client_name + "OR you can use the external Bank transaction by entering your details");
                            $("#paylocal").show();
                        }
                      //Do Something
                    },
                    error: function(xhr) {
                      //Do Something to handle error
                    }
                });
            });
            var pay=function() {
                $.ajax({
                    url: "webresources/transaction/completeTransaction/"+data.trans_id,
                    type: "POST", //send it through get method
                    data:{trans_details:'{"test1":"test"}'},
                    success: function(response) {
                        if(response.flag===0){
                            $("#main").empty();
                            $("#main").append("<h3>Sorry this transaction failed. Please try again</h3>");
                        } else {
                            window.location.href = response.redirect_url;
                        }
                      //Do Something
                    },
                    error: function(xhr) {
                      //Do Something to handle error
                    }
                });
            }
            var pay2=function() {
              var cdata={};
              cdata.name=$("#name").val();
              cdata.cardNumber=$("#cardNumber").val();
              cdata.csv = $("#csv").val();
              cdata.month = $("#month").val();
              cdata.year = $("#year").val();
              cdata.amount = parseInt(data.amount);
              console.log(cdata);
                $.ajax({
                    url: "webresources/transaction/getBuy2",
                    type: "POST", //send it through get method
                    data:cdata,
                    success: function(response) {
                      console.log(response);
                      if(typeof response.error !== 'undefined') {
                        alert(response.error);
                      } else {
                        pay();
                      }
      
                      //Do Something
                    },
                    error: function(xhr) {
                      //Do Something to handle error
                    }
                });
            }
        </script>
        <%
        }
        %>
    </body>
</html>
