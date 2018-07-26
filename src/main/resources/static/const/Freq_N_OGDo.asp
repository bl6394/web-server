<% 
Option Explicit

If Request.Form("minFreq_N_OG") = "" Then
  Session("minFreq_N_OG")=""
Else
  Session("minFreq_N_OG")=Request.Form("minFreq_N_OG")
End If

If Request.Form("maxFreq_N_OG") = "" Then
  Session("maxFreq_N_OG")=""
Else
  Session("maxFreq_N_OG")=Request.Form("maxFreq_N_OG")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              