<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minFreq_L_Mean")
strSessionMax = Session("maxFreq_L_Mean")

%>

<html>
<head>
   <title>Constraints on Freq_L_Mean</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the Freq_L_Mean or leave blank if non are desired:</h2>
  <CENTER><FORM action="Freq_L_MeanDo.asp" method="POST" name="Freq_L_MeanDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>Freq_L_Mean</TD><TD><INPUT TYPE=TEXT NAME="minFreq_L_Mean" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxFreq_L_Mean" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
