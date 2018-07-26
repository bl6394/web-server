<% 
Option Explicit

If Request.Form("minTrial") = "" Then
  Session("minTrial")=""
Else
  Session("minTrial")=Request.Form("minTrial")
End If

If Request.Form("maxTrial") = "" Then
  Session("maxTrial")=""
Else
  Session("maxTrial")=Request.Form("maxTrial")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              