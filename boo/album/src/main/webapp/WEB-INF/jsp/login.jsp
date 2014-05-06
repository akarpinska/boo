<html>
<head>
  <title>Login your album</title>
</head>
<body style="background-color: black; margin-top: 0; margin-bottom: 0;">
  <table width="800" height="100%" align="center" style="background-color: #FFFFAA;">
  <tr><td>
    <h2 align="center">Welcome to Photo Album!</h2>
    <p align="center"><font color="red">${warning}</font></p>
    <table align="center">
      <tr><td>
        <form method="post">
          <table  style="border: 1px solid black;">
            <tr><td>Username:</td><td><input type="text" name="username"></td></tr>
            <tr><td>Password:</td><td><input type="password" name="password"></td></tr>
	        <tr><td align="center" colspan="2">
                  <input type="submit" value="Login" style="width: 80; margin-right: 15">
                  <a href="/album/registration.html"><button type="button" style="width: 80; margin-left: 15">Register</button></a>
            </td></tr>
          </table>
        </form>
      </td></tr>
    </table>
  </td></tr>
  </table>
</body>
</html>
