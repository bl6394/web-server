<% 
Option Explicit

If Request.Form("minPhono_N_H") = "" Then
  Session("minPhono_N_H")=""
Else
  Session("minPhono_N_H")=Request.Form("minPhono_N_H")
End If

If Request.Form("maxPhono_N_H") = "" Then
  Session("maxPhono_N_H")=""
Else
  Session("maxPhono_N_H")=Request.Form("maxPhono_N_H")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              