<% 
Option Explicit

If Request.Form("minD_Accuracy") = "" Then
  Session("minD_Accuracy")=""
Else
  Session("minD_Accuracy")=Request.Form("minD_Accuracy")
End If

If Request.Form("maxD_Accuracy") = "" Then
  Session("maxD_Accuracy")=""
Else
  Session("maxD_Accuracy")=Request.Form("maxD_Accuracy")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              