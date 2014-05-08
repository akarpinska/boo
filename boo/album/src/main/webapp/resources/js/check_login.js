<!--
function checkform(form)
{
  var warningString = document.getElementById("warning");
  if (form.username.value == "") {
    warningString.innerHTML = "Enter username.";
    return false;
  }
  if (form.password.value == "") {
    warningString.innerHTML = "Enter password.";
    return false;
  }
  return true;
}
//-->