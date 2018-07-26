<% 
Option Explicit

If Request.Form("minFreq_N_P") = "" Then
  Session("minFreq_N_P")=""
Else
  Session("minFreq_N_P")=Request.Form("minFreq_N_P")
End If

If Request.Form("maxFreq_N_P") = "" Then
  Session("maxFreq_N_P")=""
Else
  Session("maxFreq_N_P")=Request.Form("maxFreq_N_P")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              