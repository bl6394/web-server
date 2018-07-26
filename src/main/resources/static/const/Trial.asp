<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minTrial")
strSessionMax = Session("maxTrial")

%>

<html>
<head>
   <title>Constraints on Trial</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the Trial or leave blank if non are desired:</h2>
  <CENTER><FORM action="TrialDo.asp" method="POST" name="TrialDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>Trial</TD><TD><INPUT TYPE=TEXT NAME="minTrial" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxTrial" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
