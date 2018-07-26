<% 
Option Explicit

If Request.Form("minBG_Mean") = "" Then
  Session("minBG_Mean")=""
Else
  Session("minBG_Mean")=Request.Form("minBG_Mean")
End If

If Request.Form("maxBG_Mean") = "" Then
  Session("maxBG_Mean")=""
Else
  Session("maxBG_Mean")=Request.Form("maxBG_Mean")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              