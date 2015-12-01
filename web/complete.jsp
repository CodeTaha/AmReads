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
        <title>AmReads - Where AMazon gets good READS!!</title>
    </head>
    <body>
        <%
        String trans=request.getParameter("trans");
        if (trans == null) {%>
        <h1>Sorry Broken Link!</h1>
        <h3>Purchase cannot be completed</h3>
        <%
        } else {
        %>
        <h1>Thank You for Buying from Amreads</h1>
        <p><%= trans %></p>
        <div id="main"></div>
        <script src="js/jquery.min.js"></script>
        <script>
            var act_url="<%= trans %>";
            console.log(act_url);
            var data;
            $(document).ready(function() {
                $.ajax({
                    url: "webresources/amazonClient/completePurchase/"+act_url,
                    type: "GET", //send it through get method
                    success: function(response) {
                        data=response;
                        if(data.flag==1) {
                            $("#main").append("<h3>Sorry your purchase cannot be completed</h3>");
                        } else {
                            $("#main").append("<h3>Your Book will soon be delivered to you</h3>");
                        }
                      //Do Something
                    },
                    error: function(xhr) {
                      //Do Something to handle error
                    }
                });
            });
        </script>
        <%
        }
        %>
    </body>
</html>
