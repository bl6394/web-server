<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minOLD")
strSessionMax = Session("maxOLD")

%>

<html>
<head>
   <title>Constraints on OLD</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the OLD or leave blank if non are desired:</h2>
  <CENTER><FORM action="OLDDo.asp" method="POST" name="OLDDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>OLD</TD><TD><INPUT TYPE=TEXT NAME="minOLD" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxOLD" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
