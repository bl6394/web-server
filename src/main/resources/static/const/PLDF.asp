<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minPLDF")
strSessionMax = Session("maxPLDF")

%>

<html>
<head>
   <title>Constraints on PLDF</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the PLDF or leave blank if non are desired:</h2>
  <CENTER><FORM action="PLDFDo.asp" method="POST" name="PLDFDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>PLDF</TD><TD><INPUT TYPE=TEXT NAME="minPLDF" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxPLDF" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
