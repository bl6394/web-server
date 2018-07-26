<% 
Option Explicit

If Request.Form("minI_NMG_Zscore") = "" Then
  Session("minI_NMG_Zscore")=""
Else
  Session("minI_NMG_Zscore")=Request.Form("minI_NMG_Zscore")
End If

If Request.Form("maxI_NMG_Zscore") = "" Then
  Session("maxI_NMG_Zscore")=""
Else
  Session("maxI_NMG_Zscore")=Request.Form("maxI_NMG_Zscore")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              