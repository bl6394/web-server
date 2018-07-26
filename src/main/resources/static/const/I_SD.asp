<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minI_SD")
strSessionMax = Session("maxI_SD")

%>

<html>
<head>
   <title>Constraints on I_SD</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the I_SD or leave blank if non are desired:</h2>
  <CENTER><FORM action="I_SDDo.asp" method="POST" name="I_SDDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>I_SD</TD><TD><INPUT TYPE=TEXT NAME="minI_SD" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxI_SD" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
