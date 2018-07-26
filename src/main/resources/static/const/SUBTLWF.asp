<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minSUBTLWF")
strSessionMax = Session("maxSUBTLWF")

%>

<html>
<head>
   <title>Constraints on SUBTLWF</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the SUBTLWF or leave blank if none are desired:</h2>
  <CENTER><FORM action="SUBTLWFDo.asp" method="POST" name="SUBTLWFDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>SUBTLWF</TD><TD><INPUT SUBTLWF=TEXT NAME="minSUBTLWF" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT SUBTLWF=TEXT NAME="maxSUBTLWF" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
