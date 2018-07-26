<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minBG_Mean")
strSessionMax = Session("maxBG_Mean")

%>

<html>
<head>
   <title>Constraints on BG_Mean</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the BG_Mean or leave blank if non are desired:</h2>
  <CENTER><FORM action="BG_MeanDo.asp" method="POST" name="BG_MeanDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>BG_Mean</TD><TD><INPUT TYPE=TEXT NAME="minBG_Mean" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxBG_Mean" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
