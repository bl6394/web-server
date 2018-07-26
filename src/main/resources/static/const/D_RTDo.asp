<% 
Option Explicit

If Request.Form("minD_RT") = "" Then
  Session("minD_RT")=""
Else
  Session("minD_RT")=Request.Form("minD_RT")
End If

If Request.Form("maxD_RT") = "" Then
  Session("maxD_RT")=""
Else
  Session("maxD_RT")=Request.Form("maxD_RT")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              