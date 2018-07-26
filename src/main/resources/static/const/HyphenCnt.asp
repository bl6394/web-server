<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minHyphenCnt")
strSessionMax = Session("maxHyphenCnt")

%>

<html>
<head>
   <title>Constraints on HyphenCnt</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the HyphenCnt or leave blank if non are desired:</h2>
  <CENTER><FORM action="HyphenCntDo.asp" method="POST" name="HyphenCntDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>HyphenCnt</TD><TD><INPUT TYPE=TEXT NAME="minHyphenCnt" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxHyphenCnt" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
