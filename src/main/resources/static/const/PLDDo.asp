<% 
Option Explicit

If Request.Form("minPLD") = "" Then
  Session("minPLD")=""
Else
  Session("minPLD")=Request.Form("minPLD")
End If

If Request.Form("maxPLD") = "" Then
  Session("maxPLD")=""
Else
  Session("maxPLD")=Request.Form("maxPLD")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              