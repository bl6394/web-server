<% 
Option Explicit

If Request.Form("minOLDF") = "" Then
  Session("minOLDF")=""
Else
  Session("minOLDF")=Request.Form("minOLDF")
End If

If Request.Form("maxOLDF") = "" Then
  Session("maxOLDF")=""
Else
  Session("maxOLDF")=Request.Form("maxOLDF")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              