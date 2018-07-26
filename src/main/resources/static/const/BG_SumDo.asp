<% 
Option Explicit

If Request.Form("minBG_Sum") = "" Then
  Session("minBG_Sum")=""
Else
  Session("minBG_Sum")=Request.Form("minBG_Sum")
End If

If Request.Form("maxBG_Sum") = "" Then
  Session("maxBG_Sum")=""
Else
  Session("maxBG_Sum")=Request.Form("maxBG_Sum")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              