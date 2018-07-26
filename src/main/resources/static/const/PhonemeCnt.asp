<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minPhonemeCnt")
strSessionMax = Session("maxPhonemeCnt")

%>

<html>
<head>
   <title>Constraints on PhonemeCnt</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the PhonemeCnt or leave blank if non are desired:</h2>
  <CENTER><FORM action="PhonemeCntDo.asp" method="POST" name="PhonemeCntDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>PhonemeCnt</TD><TD><INPUT TYPE=TEXT NAME="minPhonemeCnt" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxPhonemeCnt" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
