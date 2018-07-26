<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minI_NMG_Mean_RT")
strSessionMax = Session("maxI_NMG_Mean_RT")

%>

<html>
<head>
   <title>Constraints on I_NMG_Mean_RT</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the I_NMG_Mean_RT or leave blank if non are desired:</h2>
  <CENTER><FORM action="I_NMG_Mean_RTDo.asp" method="POST" name="I_NMG_Mean_RTDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>I_NMG_Mean_RT</TD><TD><INPUT TYPE=TEXT NAME="minI_NMG_Mean_RT" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxI_NMG_Mean_RT" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
