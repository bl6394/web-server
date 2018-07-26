<% 
Option Explicit

If Request.Form("minNSyll") = "" Then
  Session("minNSyll")=""
Else
  Session("minNSyll")=Request.Form("minNSyll")
End If

If Request.Form("maxNSyll") = "" Then
  Session("maxNSyll")=""
Else
  Session("maxNSyll")=Request.Form("maxNSyll")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              