<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minRT_N")
strSessionMax = Session("maxRT_N")

%>

<html>
<head>
   <title>Constraints on RT_N</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the RT_N or leave blank if non are desired:</h2>
  <CENTER><FORM action="RT_NDo.asp" method="POST" name="RT_NDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>RT_N</TD><TD><INPUT TYPE=TEXT NAME="minRT_N" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxRT_N" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
