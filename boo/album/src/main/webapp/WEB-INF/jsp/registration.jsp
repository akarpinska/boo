<script language="JavaScript" type="text/javascript">
<!--
function checkform(form)
{
  var warningString = document.getElementById("warning");
  if (form.fullname.value == "") {
    warningString.innerHTML = "Enter your full name.";
    return false;
  }
  if (form.username.value == "") {
    warningString.innerHTML = "Enter username.";
    return false;
  }
  if (form.password.value == "") {
    warningString.innerHTML = "Enter password.";
    return false;
  }
  if (form.password.value != form.confirm_password.value) {
    warningString.innerHTML = "Password does not match confirmed password.";
    return false;
  }
  return true;
}
//-->
</script>

<html>
<head>
  <title>Registration</title>
</head>
<body style="background-color: black; margin-top: 0; margin-bottom: 0;">
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
