<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minSUB_ID")
strSessionMax = Session("maxSUB_ID")

%>

<html>
<head>
   <title>Constraints on SUB_ID</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the SUB_ID or leave blank if non are desired:</h2>
  <CENTER><FORM action="SUB_IDDo.asp" method="POST" name="SUB_IDDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>SUB_ID</TD><TD><INPUT TYPE=TEXT NAME="minSUB_ID" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxSUB_ID" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
