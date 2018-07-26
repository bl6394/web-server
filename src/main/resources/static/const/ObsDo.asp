<% 
Option Explicit

If Request.Form("minObs") = "" Then
  Session("minObs")=""
Else
  Session("minObs")=Request.Form("minObs")
End If

If Request.Form("maxObs") = "" Then
  Session("maxObs")=""
Else
  Session("maxObs")=Request.Form("maxObs")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              