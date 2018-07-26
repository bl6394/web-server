<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minPLD")
strSessionMax = Session("maxPLD")

%>

<html>
<head>
   <title>Constraints on PLD</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the PLD or leave blank if non are desired:</h2>
  <CENTER><FORM action="PLDDo.asp" method="POST" name="PLDDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>PLD</TD><TD><INPUT TYPE=TEXT NAME="minPLD" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxPLD" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
