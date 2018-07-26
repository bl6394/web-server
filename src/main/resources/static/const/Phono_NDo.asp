<% 
Option Explicit

If Request.Form("minPhono_N") = "" Then
  Session("minPhono_N")=""
Else
  Session("minPhono_N")=Request.Form("minPhono_N")
End If

If Request.Form("maxPhono_N") = "" Then
  Session("maxPhono_N")=""
Else
  Session("maxPhono_N")=Request.Form("maxPhono_N")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              