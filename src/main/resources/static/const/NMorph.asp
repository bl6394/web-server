<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minNMorph")
strSessionMax = Session("maxNMorph")

%>

<html>
<head>
   <title>Constraints on NMorph</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the NMorph or leave blank if non are desired:</h2>
  <CENTER><FORM action="NMorphDo.asp" method="POST" name="NMorphDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>NMorph</TD><TD><INPUT TYPE=TEXT NAME="minNMorph" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxNMorph" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
