<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minObs")
strSessionMax = Session("maxObs")

%>

<html>
<head>
   <title>Constraints on Obs</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the Obs or leave blank if non are desired:</h2>
  <CENTER><FORM action="ObsDo.asp" method="POST" name="ObsDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>Obs</TD><TD><INPUT TYPE=TEXT NAME="minObs" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxObs" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
