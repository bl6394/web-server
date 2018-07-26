<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minNPhon")
strSessionMax = Session("maxNPhon")

%>

<html>
<head>
   <title>Constraints on NPhon</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the NPhon or leave blank if non are desired:</h2>
  <CENTER><FORM action="NPhonDo.asp" method="POST" name="NPhonDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>NPhon</TD><TD><INPUT TYPE=TEXT NAME="minNPhon" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxNPhon" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
