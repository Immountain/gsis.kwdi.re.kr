<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<head>
<script language="JavaScript">
	function end() {



	    var msg ='${msg}';
	    if(msg!=""){
             alert(msg);
        }else{
            window.opener.parent_function('${di}','${reqNum}');
        }
		self.close();
	}
	//window.opener.popup = this
</script>
</head>
<body onload="javascript:end()">
</body>
</html>