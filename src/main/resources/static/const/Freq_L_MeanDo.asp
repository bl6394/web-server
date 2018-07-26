<% 
Option Explicit

If Request.Form("minFreq_L_Mean") = "" Then
  Session("minFreq_L_Mean")=""
Else
  Session("minFreq_L_Mean")=Request.Form("minFreq_L_Mean")
End If

If Request.Form("maxFreq_L_Mean") = "" Then
  Session("maxFreq_L_Mean")=""
Else
  Session("maxFreq_L_Mean")=Request.Form("maxFreq_L_Mean")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              