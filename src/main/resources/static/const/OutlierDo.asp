<% 
Option Explicit

If Request.Form("minOutlier") = "" Then
  Session("minOutlier")=""
Else
  Session("minOutlier")=Request.Form("minOutlier")
End If

If Request.Form("maxOutlier") = "" Then
  Session("maxOutlier")=""
Else
  Session("maxOutlier")=Request.Form("maxOutlier")
End If

%>
<script language="JAVASCRIPT" >
  javascript:window.close();
</script>



              