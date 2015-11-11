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
        <h1>Hello World!</h1>
        <p><%= trans %></p>
        <div id="main"></div>
        <script src="js/jquery.min.js"></script>
        <script>
            var act_url="<%= trans %>";
            console.log(act_url);
            $.ajax({
                url: "http://localhost:8084/BookRead/webresources/transaction/getTransaction/t0ddg9u8bkn2qqnhg276tqosnf",
                type: "get", //send it through get method
                success: function(response) {
                    console.log("Taha ur Awesome",response);
                  //Do Something
                },
                error: function(xhr) {
                  //Do Something to handle error
                }
            });
        </script>
        <%
        }
        %>
    </body>
</html>
