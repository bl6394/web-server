<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minSUBTLCD")
strSessionMax = Session("maxSUBTLCD")

%>

<html>
<head>
   <title>Constraints on SUBTLCD</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the SUBTLCD or leave blank if none are desired:</h2>
  <CENTER><FORM action="SUBTLCDDo.asp" method="POST" name="SUBTLCDDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>SUBTLCD</TD><TD><INPUT SUBTLCD=TEXT NAME="minSUBTLCD" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT SUBTLCD=TEXT NAME="maxSUBTLCD" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
