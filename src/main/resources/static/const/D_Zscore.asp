<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minD_Zscore")
strSessionMax = Session("maxD_Zscore")

%>

<html>
<head>
   <title>Constraints on D_Zscore</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the D_Zscore or leave blank if non are desired:</h2>
  <CENTER><FORM action="D_ZscoreDo.asp" method="POST" name="D_ZscoreDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>D_Zscore</TD><TD><INPUT D_Zscore=TEXT NAME="minD_Zscore" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT D_Zscore=TEXT NAME="maxD_Zscore" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
