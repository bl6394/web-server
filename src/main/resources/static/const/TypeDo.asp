<% 
Option Explicit

If Request.Form("minType") = "" Then
  Session("minType")=""
Else
  Session("minType")=Request.Form("minType")
End If

If Request.Form("maxType") = "" Then
  Session("maxType")=""
Else
  Session("maxType")=Request.Form("maxType")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              