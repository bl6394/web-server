<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minD_RT")
strSessionMax = Session("maxD_RT")

%>

<html>
<head>
   <title>Constraints on D_RT</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the D_RT or leave blank if none are desired:</h2>
  <CENTER><FORM action="D_RTDo.asp" method="POST" name="D_RTDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>D_RT</TD><TD><INPUT TYPE=TEXT NAME="minD_RT" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxD_RT" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
