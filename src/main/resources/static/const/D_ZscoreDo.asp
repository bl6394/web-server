<% 
Option Explicit

If Request.Form("minD_Zscore") = "" Then
  Session("minD_Zscore")=""
Else
  Session("minD_Zscore")=Request.Form("minD_Zscore")
End If

If Request.Form("maxD_Zscore") = "" Then
  Session("maxD_Zscore")=""
Else
  Session("maxD_Zscore")=Request.Form("maxD_Zscore")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              