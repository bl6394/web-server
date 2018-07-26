<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minBG_Freq_By_Pos")
strSessionMax = Session("maxBG_Freq_By_Pos")

%>

<html>
<head>
   <title>Constraints on BG_Freq_By_Pos</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the BG_Freq_By_Pos or leave blank if non are desired:</h2>
  <CENTER><FORM action="BG_Freq_By_PosDo.asp" method="POST" name="BG_Freq_By_PosDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>BG_Freq_By_Pos</TD><TD><INPUT TYPE=TEXT NAME="minBG_Freq_By_Pos" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxBG_Freq_By_Pos" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
