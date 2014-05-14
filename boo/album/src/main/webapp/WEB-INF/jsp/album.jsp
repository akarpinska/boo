<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.album.model.api.User" %>
<%@ page import="com.album.model.api.Album" %>
<%@ page import="com.album.model.api.Photo" %>
<%@ page import="java.util.Iterator;" %>

<% User user = (User) request.getSession().getAttribute("user"); %>
<% Album album = (Album) request.getSession().getAttribute("album"); %>

<html>
<head>
  <link href='<c:url value="/resources/css/style.css" />' rel="stylesheet">
  <script src='<c:url value="/resources/js/popup.js" />'></script>
  <title>Photo Album</title>
</head>
<body>
  <table width="800" height="100%" align="center" style="background-color: #FFFFAA;">
  <tr><td valign="top">
    <p align="right" style="margin-right: 5%"><%= user.getFullName() %></p>
    <hr width="90%">
    <p align="center" id="warning" style="color: red">${warning}</p>
    <table border=2 style="margin-right: 5%; margin-left: 5%; width: 90%;">
    <%
      Iterator<Photo> photosIt = album.browsePhotos();
      int counter = 0;
      while ( counter == 0 || photosIt.hasNext() ) {
          if (counter % 5 == 0) {
            %>
            <tr height="140">
            <%
          }
          %>
          <td width="140" align="center" valign="middle">
          <%
            if (counter == 0) {
                %>
                <div id="add_photos" style="border:3px solid black; background-color:#ffffCC; padding:25px; font-size:150%; text-align:center; display:none;">
                  <form method="post" enctype="multipart/form-data">
                    <input type="file" name="files" accept="image/*" style="width: 200; align: center; font-size:14" multiple><br><br>
                    <input type="submit" value="Add" style="width: 80; margin-right: 15; font-size:16;" onClick="Popup.hide('add_photos'); return true;">
                    <button type="cancel" style="width: 80; margin-left: 15; font-size:16" onClick="Popup.hide('add_photos'); return false;">Cancel</button>
                  </from>
                </div>

                <a href="#" onclick="Popup.showModal('add_photos');return false;">
                <img src='<c:url value="/resources/img/plus.png" />' width="100" height="100">
                <p style="font-size: 12">Add photos</p>
                </a>
                <%
            }
            else {
                Photo photo = photosIt.next();
                String filePath = "img/" + photo.getFileName();
              %>
                <img src="<%= filePath %>" width="120" border=2 />
              <%
            }
          %>
          </td>
          <%
          ++counter;
      }
     %>
    </table>
  </td></tr>
  </table>
</body>
</html>
