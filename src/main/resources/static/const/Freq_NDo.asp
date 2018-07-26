<% 
Option Explicit

If Request.Form("minFreq_N") = "" Then
  Session("minFreq_N")=""
Else
  Session("minFreq_N")=Request.Form("minFreq_N")
End If

If Request.Form("maxFreq_N") = "" Then
  Session("maxFreq_N")=""
Else
  Session("maxFreq_N")=Request.Form("maxFreq_N")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              