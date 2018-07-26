<% 
Option Explicit

If Request.Form("minFreq_N_PH") = "" Then
  Session("minFreq_N_PH")=""
Else
  Session("minFreq_N_PH")=Request.Form("minFreq_N_PH")
End If

If Request.Form("maxFreq_N_PH") = "" Then
  Session("maxFreq_N_PH")=""
Else
  Session("maxFreq_N_PH")=Request.Form("maxFreq_N_PH")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              