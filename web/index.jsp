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
        <div id="main"></div>
        <script src="js/jquery.min.js"></script>
        <script>
            var act_url="<%= trans %>";
            console.log(act_url);
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
                            $("#main").append("Press ok to pay $"+data.amount+" to "+data.client_id+"-"+data.client_name);
                            $("#main").append("</br><button onclick='pay()'>OK</button>");
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
        </script>
        <%
        }
        %>
    </body>
</html>
