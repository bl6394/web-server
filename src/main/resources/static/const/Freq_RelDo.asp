<% 
Option Explicit

If Request.Form("minFreq_Rel") = "" Then
  Session("minFreq_Rel")=""
Else
  Session("minFreq_Rel")=Request.Form("minFreq_Rel")
End If

If Request.Form("maxFreq_Rel") = "" Then
  Session("maxFreq_Rel")=""
Else
  Session("maxFreq_Rel")=Request.Form("maxFreq_Rel")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              