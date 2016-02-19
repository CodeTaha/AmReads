/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
      _.templateSettings = {
        interpolate: /\{\{(.+?)\}\}/g
      };
      search();
    });
    
    var search = function() {
      $.ajax({
        url: "webresources/amazonClient/itemSearch?keywords="+$("#searchBox").val(),
        type: "POST", //send it through POST method
        success: function(response) {
          console.log("response=",response);
          createDivs(response.ItemSearchResponse.Items);
          

        }
      });  
    };
    
    var createDivs = function(data){
      var template2=_.template("<div class=\"col-sm-4 col-md-3\">\n\
        <div class=\"thumbnail\">\n\
          <img src=\"{{img_url}}\" alt=\"...\">\n\
          <div class=\"caption\">\n\
            <h3>{{title}}</h3>\n\
            <p>Price: {{list_price}}</p>\n\
            <p><button type=\"button\" class=\"btn btn-success\" role=\"button\" onclick=buy(\"{{ISBN}}\",\"{{list_price}}\")>Buy</button>\n\
              <button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#myModal\" onclick=\"fillModal('#fillm_{{ISBN}}')\" id='fillm_{{ISBN}}'>\n\
                More..\n\
              </button>\n\
            </p>\n\
          </div>\n\
        </div>\n\
      </div>");
      $("#container").empty();
      data.Item.forEach(function(item){
        //console.log(item);
        var ListPrice = typeof item.ItemAttributes.ListPrice !== 'undefined' ? item.ItemAttributes.ListPrice.FormattedPrice : "N/A";
        var ISBN = typeof item.ItemAttributes.ListPrice !== 'undefined' ? item.ItemAttributes.ListPrice.FormattedPrice : "N/A";
        $("#container").append(template2({
          title: item.ItemAttributes.Title,
          img_url:item.MediumImage.URL,
          list_price:ListPrice,
          ISBN: item.ItemAttributes.ISBN//JSON.stringify(item)
        }));
        $("#fillm_"+item.ItemAttributes.ISBN).data(item);
        //console.log("#fillm_"+item.ItemAttributes.ISBN)
      });
    };
    
    var fillModal = function(datares){
      var item = $(datares).data();
      console.log("enjoy", item);
      var goodread;
      $("#myModalLabel").html(item.ItemAttributes.Title);
      $.ajax({
        url: "webresources/amazonClient/getreview/"+item.ItemAttributes.ISBN,
        type: "GET",
        success: function(gread) {
          goodread=gread;
          //console.log("goodread=",goodread);
          $("#myModalBody").append(template3({
          img_url:  item.MediumImage.URL,
          list_price: ListPrice,
          details:  item.DetailPageURL,
          isbn: item.ItemAttributes.ISBN,
          author: item.ItemAttributes.Author,
          gr:goodread.reviews_widget
          //ISBN: item.ItemAttributes.ISBN//JSON.stringify(item)
        }));
        }
      });
      var ListPrice = typeof item.ItemAttributes.ListPrice !== 'undefined' ? item.ItemAttributes.ListPrice.FormattedPrice : "N/A";
      var template3= _.template('\
            <div class="thumbnail">\n\
              <img src="{{img_url}}" alt="...">\n\
              <div class="caption">\n\
                <h3>Thumbnail label</h3>\n\
                <div class="row">\n\
                    <div class="col-md-3 col-sm-3">Price: {{list_price}}</div>\n\
                    <div class="col-md-3 col-sm-3"><b>Author:</b>{{author}}</div>\n\
                    <div class="col-md-3 col-sm-3"><b>ISBN:</b>{{isbn}}</div>\n\
                    <div class="col-md-3 col-sm-3"><a target="_blank" href="{{details}}">More info</a></div>\n\
                    <div class="col-md-12 col-sm-12">{{gr}}</div>\n\
                </div>\n\
              </div>\n\
            </div>\n\
            <div class="modal-footer">\n\
              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>\n\
              <button type="button" class="btn btn-success" onclick=buy("{{isbn}}","{{list_price}}")>Buy</button>\n\
            </div>');
      $("#myModalBody").empty();
      
    };
    
var buy = function (isbn, amount){
  console.log("buy", isbn,amount);
  if(amount==="N/A"){
    alert("Sorry this product cannot be purchased");
    return;
  } else {
      console.log("amount",amount, amount.slice(1))
    $.ajax({
      url: "webresources/amazonClient/getBuy?isbn="+isbn+"&amount="+amount.slice(1),
      type: "GET",
      success: function(data) {
        //TODO redirect after recieving url
        console.log(data);
        window.location.href = data.transaction_url;
      }
    });
  }
};