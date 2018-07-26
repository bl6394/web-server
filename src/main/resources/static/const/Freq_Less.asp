<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minFreq_Less")
strSessionMax = Session("maxFreq_Less")

%>

<html>
<head>
   <title>Constraints on Freq_Less</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the Freq_Less or leave blank if non are desired:</h2>
  <CENTER><FORM action="Freq_LessDo.asp" method="POST" name="Freq_LessDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>Freq_Less</TD><TD><INPUT TYPE=TEXT NAME="minFreq_Less" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxFreq_Less" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
