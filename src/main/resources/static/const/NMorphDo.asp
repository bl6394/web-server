<% 
Option Explicit

If Request.Form("minNMorph") = "" Then
  Session("minNMorph")=""
Else
  Session("minNMorph")=Request.Form("minNMorph")
End If

If Request.Form("maxNMorph") = "" Then
  Session("maxNMorph")=""
Else
  Session("maxNMorph")=Request.Form("maxNMorph")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              