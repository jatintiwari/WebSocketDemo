<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="jatin tiwari">
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title>Login-CHAT || Websocket</title>
		<link href="resources/lib/css/bootstrap.min.css" rel="stylesheet">
		<link href="resources/lib/css/ladda-themeless.min.css" rel="stylesheet">
		<!-- ===in case it is required=== -->
		<link href="resources/lib/css/styles.css" rel="stylesheet">
		<!-- template scripts -->
		<script type="text/javascript" src="resources/lib/ladda/spin.min.js"></script>
		<script type="text/javascript" src="resources/lib/ladda/ladda.min.js"></script>
		
		<style>
        body{
          background-color: #7DC8F1;
          overflow-x: hidden;
        }
        .modal-footer {   border-top: 0px; }
        .has-error{
          font-size: 15px;
          margin-bottom: 0px;
        }
        .jumbotron{
          -webkit-box-shadow: 2px 2px 5px 0px rgba(0,0,0,0.75);
        -moz-box-shadow: 2px 2px 5px 0px rgba(0,0,0,0.75);
        box-shadow: 2px 2px 5px 0px rgba(0,0,0,0.75);
        }
        .active{
          color: red;
        }
        a:hover{
          color: red;  
        }
        a:visited, a:link
        {
            text-decoration: none;
            outline: none;

        }
        
    </style>
</head>
<body>
	<div id="idAppSpace"></div>
	<script type="text/javascript" data-main="resources/modules/login/loginApp"
		src="resources/lib/require/require.js"></script>
</body>
</html>