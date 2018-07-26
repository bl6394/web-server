<% 
Option Explicit

If Request.Form("minI_SD") = "" Then
  Session("minI_SD")=""
Else
  Session("minI_SD")=Request.Form("minI_SD")
End If

If Request.Form("maxI_SD") = "" Then
  Session("maxI_SD")=""
Else
  Session("maxI_SD")=Request.Form("maxI_SD")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              