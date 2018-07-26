<% 
Option Explicit

If Request.Form("minSUBTLWF") = "" Then
  Session("minSUBTLWF")=""
Else
  Session("minSUBTLWF")=Request.Form("minSUBTLWF")
End If

If Request.Form("maxSUBTLWF") = "" Then
  Session("maxSUBTLWF")=""
Else
  Session("maxSUBTLWF")=Request.Form("maxSUBTLWF")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              