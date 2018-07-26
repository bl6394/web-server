<% 
Option Explicit

If Request.Form("minHyphenCnt") = "" Then
  Session("minHyphenCnt")=""
Else
  Session("minHyphenCnt")=Request.Form("minHyphenCnt")
End If

If Request.Form("maxHyphenCnt") = "" Then
  Session("maxHyphenCnt")=""
Else
  Session("maxHyphenCnt")=Request.Form("maxHyphenCnt")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              