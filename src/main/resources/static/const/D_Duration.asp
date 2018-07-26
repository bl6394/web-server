<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minD_Duration")
strSessionMax = Session("maxD_Duration")

%>

<html>
<head>
   <title>Constraints on D_Duration</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the D_Duration or leave blank if non are desired:</h2>
  <CENTER><FORM action="D_DurationDo.asp" method="POST" name="D_DurationDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>D_Duration</TD><TD><INPUT D_Duration=TEXT NAME="minD_Duration" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT D_Duration=TEXT NAME="maxD_Duration" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
