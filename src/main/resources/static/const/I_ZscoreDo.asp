<% 
Option Explicit

If Request.Form("minI_Zscore") = "" Then
  Session("minI_Zscore")=""
Else
  Session("minI_Zscore")=Request.Form("minI_Zscore")
End If

If Request.Form("maxI_Zscore") = "" Then
  Session("maxI_Zscore")=""
Else
  Session("maxI_Zscore")=Request.Form("maxI_Zscore")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              