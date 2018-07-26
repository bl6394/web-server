<% 
Option Explicit

If Request.Form("minOG_N") = "" Then
  Session("minOG_N")=""
Else
  Session("minOG_N")=Request.Form("minOG_N")
End If

If Request.Form("maxOG_N") = "" Then
  Session("maxOG_N")=""
Else
  Session("maxOG_N")=Request.Form("maxOG_N")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              