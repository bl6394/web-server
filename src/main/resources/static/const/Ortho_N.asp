<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minOrtho_N")
strSessionMax = Session("maxOrtho_N")

%>

<html>
<head>
   <title>Constraints on Ortho_N</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the Ortho_N or leave blank if non are desired:</h2>
  <CENTER><FORM action="Ortho_NDo.asp" method="POST" name="Ortho_NDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>Ortho_N</TD><TD><INPUT TYPE=TEXT NAME="minOrtho_N" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxOrtho_N" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
