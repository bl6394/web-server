<% 
Option Explicit

If Request.Form("minI_NMG_SD") = "" Then
  Session("minI_NMG_SD")=""
Else
  Session("minI_NMG_SD")=Request.Form("minI_NMG_SD")
End If

If Request.Form("maxI_NMG_SD") = "" Then
  Session("maxI_NMG_SD")=""
Else
  Session("maxI_NMG_SD")=Request.Form("maxI_NMG_SD")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              