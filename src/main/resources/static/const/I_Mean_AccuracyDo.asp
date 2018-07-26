<% 
Option Explicit

If Request.Form("minI_Mean_Accuracy") = "" Then
  Session("minI_Mean_Accuracy")=""
Else
  Session("minI_Mean_Accuracy")=Request.Form("minI_Mean_Accuracy")
End If

If Request.Form("maxI_Mean_Accuracy") = "" Then
  Session("maxI_Mean_Accuracy")=""
Else
  Session("maxI_Mean_Accuracy")=Request.Form("maxI_Mean_Accuracy")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              