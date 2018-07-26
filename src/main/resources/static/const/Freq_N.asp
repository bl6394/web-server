<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minFreq_N")
strSessionMax = Session("maxFreq_N")

%>

<html>
<head>
   <title>Constraints on Freq_N</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the Freq_N or leave blank if non are desired:</h2>
  <CENTER><FORM action="Freq_NDo.asp" method="POST" name="Freq_NDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>Freq_N</TD><TD><INPUT TYPE=TEXT NAME="minFreq_N" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxFreq_N" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
