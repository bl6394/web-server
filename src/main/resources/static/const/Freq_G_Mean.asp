<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minFreq_G_Mean")
strSessionMax = Session("maxFreq_G_Mean")

%>

<html>
<head>
   <title>Constraints on Freq_G_Mean</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the Freq_G_Mean or leave blank if non are desired:</h2>
  <CENTER><FORM action="Freq_G_MeanDo.asp" method="POST" name="Freq_G_MeanDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>Freq_G_Mean</TD><TD><INPUT TYPE=TEXT NAME="minFreq_G_Mean" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxFreq_G_Mean" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
