<% 
Option Explicit

If Request.Form("minFreq_Less") = "" Then
  Session("minFreq_Less")=""
Else
  Session("minFreq_Less")=Request.Form("minFreq_Less")
End If

If Request.Form("maxFreq_Less") = "" Then
  Session("maxFreq_Less")=""
Else
  Session("maxFreq_Less")=Request.Form("maxFreq_Less")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              