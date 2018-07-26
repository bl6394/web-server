<% 
Option Explicit

If Request.Form("minFreq_G_Mean") = "" Then
  Session("minFreq_G_Mean")=""
Else
  Session("minFreq_G_Mean")=Request.Form("minFreq_G_Mean")
End If

If Request.Form("maxFreq_G_Mean") = "" Then
  Session("maxFreq_G_Mean")=""
Else
  Session("maxFreq_G_Mean")=Request.Form("maxFreq_G_Mean")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              