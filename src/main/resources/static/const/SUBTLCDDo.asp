<% 
Option Explicit

If Request.Form("minSUBTLCD") = "" Then
  Session("minSUBTLCD")=""
Else
  Session("minSUBTLCD")=Request.Form("minSUBTLCD")
End If

If Request.Form("maxSUBTLCD") = "" Then
  Session("maxSUBTLCD")=""
Else
  Session("maxSUBTLCD")=Request.Form("maxSUBTLCD")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              