<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minOLDF")
strSessionMax = Session("maxOLDF")

%>

<html>
<head>
   <title>Constraints on OLDF</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the OLDF or leave blank if non are desired:</h2>
  <CENTER><FORM action="OLDFDo.asp" method="POST" name="OLDFDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>OLDF</TD><TD><INPUT TYPE=TEXT NAME="minOLDF" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxOLDF" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
