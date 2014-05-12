<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.album.model.api.User" %>

<% User user = (User) request.getSession().getAttribute("user"); %>

<html>
<head>
  <link href='<c:url value="/resources/css/style.css" />' rel="stylesheet">
  <title>Photo Album</title>
</head>
<body>
  <table width="800" height="100%" align="center" style="background-color: #FFFFAA;">
  <tr><td valign="top">
    <p align="right" style="margin-right: 5%"><%= user.getFullName() %></p>
    <hr width="90%">
    <form>
      <table  style="border: 1px solid black;">
        <tr><td>Album name:</td><td><input type="text" name="album_name"></td></tr>
        <tr><td>Images:</td><td><input type="file" name="files" multiple></td></tr>
      </table>
    </form>
  </td></tr>
  </table>
</body>
</html>
