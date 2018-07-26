<% 
Option Explicit

If Request.Form("minNPhon") = "" Then
  Session("minNPhon")=""
Else
  Session("minNPhon")=Request.Form("minNPhon")
End If

If Request.Form("maxNPhon") = "" Then
  Session("maxNPhon")=""
Else
  Session("maxNPhon")=Request.Form("maxNPhon")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              