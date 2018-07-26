<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minOutlier")
strSessionMax = Session("maxOutlier")

%>

<html>
<head>
   <title>Constraints on Outlier</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the Outlier or leave blank if non are desired:</h2>
  <CENTER><FORM action="OutlierDo.asp" method="POST" name="OutlierDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>Outlier</TD><TD><INPUT TYPE=TEXT NAME="minOutlier" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxOutlier" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
