<% 
Option Explicit

If Request.Form("minBG_Freq_By_Pos") = "" Then
  Session("minBG_Freq_By_Pos")=""
Else
  Session("minBG_Freq_By_Pos")=Request.Form("minBG_Freq_By_Pos")
End If

If Request.Form("maxBG_Freq_By_Pos") = "" Then
  Session("maxBG_Freq_By_Pos")=""
Else
  Session("maxBG_Freq_By_Pos")=Request.Form("maxBG_Freq_By_Pos")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              