<% 
Option Explicit

If Request.Form("minSUB_ID") = "" Then
  Session("minSUB_ID")=""
Else
  Session("minSUB_ID")=Request.Form("minSUB_ID")
End If

If Request.Form("maxSUB_ID") = "" Then
  Session("maxSUB_ID")=""
Else
  Session("maxSUB_ID")=Request.Form("maxSUB_ID")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              