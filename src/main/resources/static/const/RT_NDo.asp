<% 
Option Explicit

If Request.Form("minRT_N") = "" Then
  Session("minRT_N")=""
Else
  Session("minRT_N")=Request.Form("minRT_N")
End If

If Request.Form("maxRT_N") = "" Then
  Session("maxRT_N")=""
Else
  Session("maxRT_N")=Request.Form("maxRT_N")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              