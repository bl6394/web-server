<% 
Option Explicit

If Request.Form("minLgSUBTLWF") = "" Then
  Session("minLgSUBTLWF")=""
Else
  Session("minLgSUBTLWF")=Request.Form("minLgSUBTLWF")
End If

If Request.Form("maxLgSUBTLWF") = "" Then
  Session("maxLgSUBTLWF")=""
Else
  Session("maxLgSUBTLWF")=Request.Form("maxLgSUBTLWF")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              