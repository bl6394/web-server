<% 
Option Explicit

If Request.Form("minI_Mean_RT") = "" Then
  Session("minI_Mean_RT")=""
Else
  Session("minI_Mean_RT")=Request.Form("minI_Mean_RT")
End If

If Request.Form("maxI_Mean_RT") = "" Then
  Session("maxI_Mean_RT")=""
Else
  Session("maxI_Mean_RT")=Request.Form("maxI_Mean_RT")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              