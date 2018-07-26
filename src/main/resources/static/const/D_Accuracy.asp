<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minD_Accuracy")
strSessionMax = Session("maxD_Accuracy")

%>

<html>
<head>
   <title>Constraints on D_Accuracy</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the D_Accuracy or leave blank if non are desired:</h2>
  <CENTER><FORM action="D_AccuracyDo.asp" method="POST" name="D_AccuracyDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>D_Accuracy</TD><TD><INPUT TYPE=TEXT NAME="minD_Accuracy" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxD_Accuracy" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
