<% 
Option Explicit

If Request.Form("minD_Duration") = "" Then
  Session("minD_Duration")=""
Else
  Session("minD_Duration")=Request.Form("minD_Duration")
End If

If Request.Form("maxD_Duration") = "" Then
  Session("maxD_Duration")=""
Else
  Session("maxD_Duration")=Request.Form("maxD_Duration")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              