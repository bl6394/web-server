<% 
Option Explicit

If Request.Form("minPhonemeCnt") = "" Then
  Session("minPhonemeCnt")=""
Else
  Session("minPhonemeCnt")=Request.Form("minPhonemeCnt")
End If

If Request.Form("maxPhonemeCnt") = "" Then
  Session("maxPhonemeCnt")=""
Else
  Session("maxPhonemeCnt")=Request.Form("maxPhonemeCnt")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              