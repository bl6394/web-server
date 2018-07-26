<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minNSyll")
strSessionMax = Session("maxNSyll")

%>

<html>
<head>
   <title>Constraints on NSyll</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the NSyll or leave blank if non are desired:</h2>
  <CENTER><FORM action="NSyllDo.asp" method="POST" name="NSyllDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>NSyll</TD><TD><INPUT TYPE=TEXT NAME="minNSyll" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxNSyll" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
