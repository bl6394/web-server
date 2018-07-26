<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minLgSUBTLCD")
strSessionMax = Session("maxLgSUBTLCD")

%>

<html>
<head>
   <title>Constraints on LgSUBTLCD</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the LgSUBTLCD or leave blank if none are desired:</h2>
  <CENTER><FORM action="LgSUBTLCDDo.asp" method="POST" name="LgSUBTLCDDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>LgSUBTLCD</TD><TD><INPUT LgSUBTLCD=TEXT NAME="minLgSUBTLCD" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT LgSUBTLCD=TEXT NAME="maxLgSUBTLCD" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
