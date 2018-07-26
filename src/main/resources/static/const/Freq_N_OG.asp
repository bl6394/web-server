<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minFreq_N_OG")
strSessionMax = Session("maxFreq_N_OG")

%>

<html>
<head>
   <title>Constraints on Freq_N_OG</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the Freq_N_OG or leave blank if non are desired:</h2>
  <CENTER><FORM action="Freq_N_OGDo.asp" method="POST" name="Freq_N_OGDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>Freq_N_OG</TD><TD><INPUT TYPE=TEXT NAME="minFreq_N_OG" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxFreq_N_OG" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
