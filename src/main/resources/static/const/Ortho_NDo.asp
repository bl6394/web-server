<% 
Option Explicit

If Request.Form("minOrtho_N") = "" Then
  Session("minOrtho_N")=""
Else
  Session("minOrtho_N")=Request.Form("minOrtho_N")
End If

If Request.Form("maxOrtho_N") = "" Then
  Session("maxOrtho_N")=""
Else
  Session("maxOrtho_N")=Request.Form("maxOrtho_N")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              