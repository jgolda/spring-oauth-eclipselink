<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Title</title>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
</head>
<body>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.6/angular.min.js"></script>
<script src="${pageContext.request.contextPath}/static/hello_world_controller.js"></script>
<div ng-app="HelloWorldApp">
	<div ng-controller="HelloWorldController">
		<h1>{{greeting}}</h1>
		<button ng-click="noEloxD()">xD</button>
		<button ng-click="noEloXD()">XD</button>
		<button ng-click="noEloxP()">xP</button>
		<br/>
		<input type="text" ng-model="someInput">
		<p>Some input value: {{someInput}}</p>
		<p>Some input uppercase value: {{someInput | uppercase}}</p>
		<p>Some input uppercase value: {{someInput | lowercase}}</p>
		<p>Some input pokemon value: {{someInput | pokemon}}</p>
		<p>wiadomosc hello: {{getHelloMessage()}}</p>
		<div>
			<h4>Blacklisted passphrases:</h4>
			<input type="text" placeholder="Filter" ng-model="filterQuery"/>
			<ul>
				<li ng-repeat="val in someArray | filter:filterQuery">{{val}}</li>
			</ul>
		</div>
		<h3>Reusable list content here:</h3>
		{{reusableListModel}}

		<h4>Actual reusable list:</h4>
		<reusable-list list="reusableListModel"></reusable-list>
		<div>some text in div</div>
		some static text here
		<p>some text in paragraph</p>

		<div class=""></div>
	</div>
</div>
</body>
</html>
