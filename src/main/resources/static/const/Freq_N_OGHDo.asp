<% 
Option Explicit

If Request.Form("minFreq_N_OGH") = "" Then
  Session("minFreq_N_OGH")=""
Else
  Session("minFreq_N_OGH")=Request.Form("minFreq_N_OGH")
End If

If Request.Form("maxFreq_N_OGH") = "" Then
  Session("maxFreq_N_OGH")=""
Else
  Session("maxFreq_N_OGH")=Request.Form("maxFreq_N_OGH")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              