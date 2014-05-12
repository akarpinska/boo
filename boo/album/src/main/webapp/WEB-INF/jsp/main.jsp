<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.album.model.api.User" %>
<%@ page import="com.album.model.api.Album" %>
<%@ page import="java.util.List;" %>

<% User user = (User) request.getSession().getAttribute("user"); %>

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
    <table border=2 style="margin-right: 5%; margin-left: 5%; width: 90%;">
    <%
      List<Album> albums = user.getAlbums();
      for ( int i = 0; i <= albums.size()+1; i++) {
          if (i % 5 == 0) {
            %>
            <tr height="140">
            <%
          }
          %>
          <td width="140" align="center" valign="middle">
          <%
            if (i == 0) {
                %>
                <div id="create_album" style="border:3px solid black; background-color:#ffffCC; padding:25px; font-size:150%; text-align:center; display:none;">
                  <form method="post">
                    Album name: <input type="text" name="album_name"><br><br>
                    <input type="submit" value="Create" style="width: 80; margin-right: 15; font-size:16;" onClick="Popup.hide('create_album'); return true;">
                    <button type="cancel" style="width: 80; margin-left: 15; font-size:16" onClick="Popup.hide('create_album'); return true;">Cancel</button>
                  </from>
                </div>

                <a href="#" onclick="Popup.showModal('create_album');return false;">
                <img src='<c:url value="/resources/img/plus.png" />' width="100" height="100">
                <p style="font-size: 12">New album</p>
                </a>
                <%
            }
            else {
              %>
                <p>Empty</p>
              <%
            }
          %>
          </td>
          <%
      }
     %>
    </table>
  </td></tr>
  </table>
</body>
</html>
