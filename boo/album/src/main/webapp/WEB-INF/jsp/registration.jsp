<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <link href='<c:url value="/resources/css/style.css" />' rel="stylesheet">
  <script src='<c:url value="/resources/js/check_registration.js" />'></script>
  <title>Registration</title>
</head>
<body>
  <table width="800" height="100%" align="center" style="background-color: #FFFFAA;">
  <tr><td>
   <h2 align="center">Create a new Photo Album account</h2>
   <p align="center" id="warning" style="color: red">${warning}</p>
      <table align="center">
        <tr><td>
          <form method="post" onsubmit="return checkform(this);">
            <table  style="border: 1px solid black;">
              <tr><td>Full name:</td><td><input type="text" name="fullname"></td></tr>
              <tr><td>Username:</td><td><input type="text" name="username"></td></tr>
              <tr><td>Password:</td><td><input type="password" name="password"></td></tr>
              <tr><td>Confirm password:</td><td><input type="password" name="confirm_password"></td></tr>
              <tr><td align="center" colspan="2">
                    <input type="submit" value="Register" style="width: 80;">
              </td></tr>
            </table>
          </form>
        </td></tr>
      </table>
  </td></tr>
  </table>
</body>
</html>
