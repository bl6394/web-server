<% Option Explicit

Dim strSessionMin, strSessionMax

strSessionMin = Session("minPhono_N")
strSessionMax = Session("maxPhono_N")

%>

<html>
<head>
   <title>Constraints on Phono_N</title>
</head>

<body>
  <h2>Please enter any desired contraints upon the Phono_N or leave blank if non are desired:</h2>
  <CENTER><FORM action="Phono_NDo.asp" method="POST" name="Phono_NDo">
  <table border="0">
  <tr><th align="left">Attribute</th><th align="left">Minimum Value</th><th align="left">Maximum Value</th></tr>
  <TR ALIGN=LEFT><TD>Phono_N</TD><TD><INPUT TYPE=TEXT NAME="minPhono_N" VALUE="<% Response.Write strSessionMin %>" ></TD><TD><INPUT TYPE=TEXT NAME="maxPhono_N" VALUE="<% Response.Write strSessionMax %>" ></TR></table>
  <BR>
  <input type="SUBMIT" Value="Submit Constraint">
  </FORM></CENTER>
</body>
</html>
