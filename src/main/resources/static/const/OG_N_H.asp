<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minOG_N_H")
strSessionMax = Session("maxOG_N_H")

%>

<html>
<head>
   <title>Constraints on OG_N_H</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the OG_N_H or leave blank if non are desired:</h2>
  <CENTER><FORM action="OG_N_HDo.asp" method="POST" name="OG_N_HDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>OG_N_H</TD><TD><INPUT TYPE=TEXT NAME="minOG_N_H" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxOG_N_H" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
