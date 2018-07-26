<% 
Option Explicit

If Request.Form("minOG_N_H") = "" Then
  Session("minOG_N_H")=""
Else
  Session("minOG_N_H")=Request.Form("minOG_N_H")
End If

If Request.Form("maxOG_N_H") = "" Then
  Session("maxOG_N_H")=""
Else
  Session("maxOG_N_H")=Request.Form("maxOG_N_H")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              