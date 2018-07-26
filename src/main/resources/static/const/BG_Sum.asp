<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minBG_Sum")
strSessionMax = Session("maxBG_Sum")

%>

<html>
<head>
   <title>Constraints on BG_Sum</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the BG_Sum or leave blank if non are desired:</h2>
  <CENTER><FORM action="BG_SumDo.asp" method="POST" name="BG_SumDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>BG_Sum</TD><TD><INPUT TYPE=TEXT NAME="minBG_Sum" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxBG_Sum" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
