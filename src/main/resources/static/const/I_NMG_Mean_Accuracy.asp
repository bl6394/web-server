<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minI_NMG_Mean_Accuracy")
strSessionMax = Session("maxI_NMG_Mean_Accuracy")

%>

<html>
<head>
   <title>Constraints on I_NMG_Mean_Accuracy</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the I_NMG_Mean_Accuracy or leave blank if non are desired:</h2>
  <CENTER><FORM action="I_NMG_Mean_AccuracyDo.asp" method="POST" name="I_NMG_Mean_AccuracyDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>I_NMG_Mean_Accuracy</TD><TD><INPUT TYPE=TEXT NAME="minI_NMG_Mean_Accuracy" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxI_NMG_Mean_Accuracy" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
