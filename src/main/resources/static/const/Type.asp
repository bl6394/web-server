<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minType")
strSessionMax = Session("maxType")

%>

<html>
<head>
   <title>Constraints on Type</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the Type or leave blank if non are desired:</h2>
  <CENTER><FORM action="TypeDo.asp" method="POST" name="TypeDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>Type</TD><TD><INPUT TYPE=TEXT NAME="minType" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxType" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
