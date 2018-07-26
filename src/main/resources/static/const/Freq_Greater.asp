<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minFreq_Greater")
strSessionMax = Session("maxFreq_Greater")

%>

<html>
<head>
   <title>Constraints on Freq_Greater</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the Freq_Greater or leave blank if non are desired:</h2>
  <CENTER><FORM action="Freq_GreaterDo.asp" method="POST" name="Freq_GreaterDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>Freq_Greater</TD><TD><INPUT TYPE=TEXT NAME="minFreq_Greater" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxFreq_Greater" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
