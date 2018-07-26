<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minFreq_N_PH")
strSessionMax = Session("maxFreq_N_PH")

%>

<html>
<head>
   <title>Constraints on Freq_N_PH</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the Freq_N_PH or leave blank if non are desired:</h2>
  <CENTER><FORM action="Freq_N_PHDo.asp" method="POST" name="Freq_N_PHDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>Freq_N_PH</TD><TD><INPUT TYPE=TEXT NAME="minFreq_N_PH" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxFreq_N_PH" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
