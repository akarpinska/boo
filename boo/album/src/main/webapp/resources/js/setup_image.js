<!--
function setupImage(target, source)
{
  target.src = source.src;
  if (source.width > source.height) {
    target.width = "640";
    target.height = (640/source.width)*source.height;
  }
  else {
    target.height = "800";
    target.width = (800/source.height)*source.width;
  }
}
//-->