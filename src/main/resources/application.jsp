<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Title</title>
</head>
<body>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.6/angular.min.js"></script>
<script src="hello_world_controller.js"></script>
<div ng-app="HelloWorldApp">
	<div ng-controller="HelloWorldController">
		<h1>{{greeting}}</h1>
	</div>
</div>
</body>
</html>
