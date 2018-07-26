<% 
Option Explicit

If Request.Form("minOLD") = "" Then
  Session("minOLD")=""
Else
  Session("minOLD")=Request.Form("minOLD")
End If

If Request.Form("maxOLD") = "" Then
  Session("maxOLD")=""
Else
  Session("maxOLD")=Request.Form("maxOLD")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              