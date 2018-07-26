<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minFreq_Rel")
strSessionMax = Session("maxFreq_Rel")

%>

<html>
<head>
   <title>Constraints on Freq_Rel</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the Freq_Rel or leave blank if non are desired:</h2>
  <CENTER><FORM action="Freq_RelDo.asp" method="POST" name="Freq_RelDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>Freq_Rel</TD><TD><INPUT TYPE=TEXT NAME="minFreq_Rel" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxFreq_Rel" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
