<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minFreq_N_OGH")
strSessionMax = Session("maxFreq_N_OGH")

%>

<html>
<head>
   <title>Constraints on Freq_N_OGH</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the Freq_N_OGH or leave blank if non are desired:</h2>
  <CENTER><FORM action="Freq_N_OGHDo.asp" method="POST" name="Freq_N_OGHDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>Freq_N_OGH</TD><TD><INPUT TYPE=TEXT NAME="minFreq_N_OGH" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxFreq_N_OGH" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
