<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.album.model.api.User" %>
<%@ page import="com.album.model.api.Album" %>
<%@ page import="java.util.Iterator;" %>

<% User user = (User) request.getSession().getAttribute("user"); %>
<% Album album = (Album) request.getSession().getAttribute("album"); %>

<html>
<head>
  <link href='<c:url value="/resources/css/style.css" />' rel="stylesheet">
  <script src='<c:url value="/resources/js/popup.js" />'></script>
  <script src='<c:url value="/resources/js/setup_image.js" />'></script>
  <title>Photo Album</title>
</head>
<body>
  <table width="800" height="100%" align="center" style="background-color: #FFFFAA;" id="main_table">
  <tr><td valign="top">
    <%@ include file="header.jsp" %>
    <a href="../main.htm" align="left" style="margin-left: 5%">Back to albums</a>
    <hr width="90%">

    <div id="add_photos" style="border:3px solid black; background-color:#ffffCC; padding:25px; font-size:150%; text-align:center; display:none;">
      <form method="post" enctype="multipart/form-data">
        <input type="file" name="files" accept="image/*" style="width: 200; align: center; font-size:14" multiple><br><br>
        <input type="submit" value="Add" style="width: 80; margin-right: 15; font-size:16;" onClick="Popup.hide('add_photos'); return true;">
        <button type="cancel" style="width: 80; margin-left: 15; font-size:16" onClick="Popup.hide('add_photos'); return false;">Cancel</button>
      </from>
    </div>

    <div id="photo_div" style="border:3px solid black; background-color:#ffffCC; padding:25px; text-align:center; display:none;">
      <img id="big_img" src="" width="640" /><br>
      <button type="cancel" style="width: 80; font-size:16" onClick="Popup.hide('photo_div'); return false;">Close</button>
    </div>

    <p align="center" id="warning" style="color: red">${warning}</p>
    <table style="margin-right: 5%; margin-left: 5%; width: 90%;">
      <tr height="140">

        <td width="140" align="center" valign="middle">
          <a href="#" onclick="Popup.showModal('add_photos');return false;">
            <img src='<c:url value="/resources/img/plus.png" />' width="100" height="100">
            <p style="font-size: 12">Add photos</p>
          </a>
        </td>

        <c:set var="photos_counter" scope="page" value="0"/>
        <c:forEach items="<%= album.getPhotoIds() %>" var="photoId">
          <c:if test="${photos_counter % 5 == 4}">
            </tr>
            <tr height="140">
          </c:if>

          <c:set var="photos_counter" scope="page" value="${photos_counter+1}" />

          <td width="140" align="center" valign="middle">
            <img src='<c:out value="img/small/${photoId}" />' width="120" border=2 onclick="setupImage(document.getElementById('big_img'), this, '<c:out value="img/${photoId}" />'); Popup.showModal('photo_div', null, 'center center', null);" />
          </td>
        </c:forEach>

      </tr>
    </table>
  </td></tr>
  </table>
</body>
</html>
