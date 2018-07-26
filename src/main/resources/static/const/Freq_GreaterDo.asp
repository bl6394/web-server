<% 
Option Explicit

If Request.Form("minFreq_Greater") = "" Then
  Session("minFreq_Greater")=""
Else
  Session("minFreq_Greater")=Request.Form("minFreq_Greater")
End If

If Request.Form("maxFreq_Greater") = "" Then
  Session("maxFreq_Greater")=""
Else
  Session("maxFreq_Greater")=Request.Form("maxFreq_Greater")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              