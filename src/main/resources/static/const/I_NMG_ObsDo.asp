<% 
Option Explicit

If Request.Form("minI_NMG_Obs") = "" Then
  Session("minI_NMG_Obs")=""
Else
  Session("minI_NMG_Obs")=Request.Form("minI_NMG_Obs")
End If

If Request.Form("maxI_NMG_Obs") = "" Then
  Session("maxI_NMG_Obs")=""
Else
  Session("maxI_NMG_Obs")=Request.Form("maxI_NMG_Obs")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              