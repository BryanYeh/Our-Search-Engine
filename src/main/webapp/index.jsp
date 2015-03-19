<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Search Page</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<img src="images/logo.jpg" id="brand">
				<form class="form-inline" action="Seearch" method="POST" enctype="application/x-www-form-urlencoded">
					<div class="form-group">
						<div class="input-group">
							<input type="text" class="form-control" name="q">
							<input type="hidden" value="www.yahoo.com" name="s">
							<div class="input-group-btn">
								<button class="btn btn-primary" type="submit" id="submit" name="submit">
									<i class="glyphicon glyphicon-search"></i>
								</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- This is special DO NOT DELETE -->
		<div class="row">
			<img id="haha" src="">
		</div>
	</div>
		
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
		<script type="text/javascript">
		$("#brand").click(function() {
			$("#haha").attr('src', 'images/smile.jpg');
			$(function() {
				window.setInterval("$('#haha').toggle();", 20);
			});
			setTimeout(function() {
				$("#haha").attr('src', '');
			}, 50);
		});
	</script>
</body>
</html>