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