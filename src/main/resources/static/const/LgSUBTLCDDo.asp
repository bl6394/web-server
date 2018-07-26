<% 
Option Explicit

If Request.Form("minLgSUBTLCD") = "" Then
  Session("minLgSUBTLCD")=""
Else
  Session("minLgSUBTLCD")=Request.Form("minLgSUBTLCD")
End If

If Request.Form("maxLgSUBTLCD") = "" Then
  Session("maxLgSUBTLCD")=""
Else
  Session("maxLgSUBTLCD")=Request.Form("maxLgSUBTLCD")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              