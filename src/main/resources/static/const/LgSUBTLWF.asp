<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minLgSUBTLWF")
strSessionMax = Session("maxLgSUBTLWF")

%>

<html>
<head>
   <title>Constraints on LgSUBTLWF</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the LgSUBTLWF or leave blank if none are desired:</h2>
  <CENTER><FORM action="LgSUBTLWFDo.asp" method="POST" name="LgSUBTLWFDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>LgSUBTLWF</TD><TD><INPUT LgSUBTLWF=TEXT NAME="minLgSUBTLWF" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT LgSUBTLWF=TEXT NAME="maxLgSUBTLWF" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
