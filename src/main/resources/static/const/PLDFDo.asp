<% 
Option Explicit

If Request.Form("minPLDF") = "" Then
  Session("minPLDF")=""
Else
  Session("minPLDF")=Request.Form("minPLDF")
End If

If Request.Form("maxPLDF") = "" Then
  Session("maxPLDF")=""
Else
  Session("maxPLDF")=Request.Form("maxPLDF")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              