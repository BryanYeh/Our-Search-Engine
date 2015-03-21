<%@ page import="java.util.List" %>
<%@ page import="web.RankedLink" %>

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
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span>
				</button>
				<a href="index.jsp" class="navbar-brand" id="brand">C&#149;E&#149;I&#149;R</a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<form class="navbar-form navbar-left" role="search" action="Seearch" method="POST" enctype="application/x-www-form-urlencoded">
					<div class="input-group">
						<input type="hidden" name="s" value="www.apple.com"> 
						<input type="text" name="q" class="form-control"> 
						<span class="input-group-btn">
							<button class="btn btn-default" type="button">
								<span class="glyphicon glyphicon-search"></span>
							</button>
						</span>
					</div>
				</form>
			</div>
		</div>
	</nav>
	<div class="jumbotron"></div>
	<div class="container" id="jsonStuff">
		<%
			List<String> specials = (List<String>) request.getAttribute("special");
			for(int i = 0; i < specials.size(); i++){
		%>
		<div class="row result" id="<%=(-1-i)%>">
			<div class="col-md-10">
				<h2><a href="<%=specials.get(i+1) %>"><%=specials.get(i) %></a></h2>
				<p>
					<%
						if(specials.get(i+1).toString().length()>128)
							out.println(specials.get(i+1).toString().substring(0, 125));
						else
							out.println(specials.get(i+1));
					%>
				</p>
				<p><%=specials.get(i+2) %></p>
			</div>
		</div>
		<% 
			i+=2;
			}
		%>
		<hr>
		<%
			List<RankedLink> ourspecial = (List<RankedLink>) request.getAttribute("special2");
			//int whatever = 50;
			//if(ourspecial.size()<50)
			//	whatever = ourspecial.size();
			for(int i = 0; i < ourspecial.size(); i++){
		%>
		<div class="row result " id="<%= i %>">
			<div class="col-md-10">
				<h2><a href="<%=ourspecial.get(i).getUrl() %>"><%=ourspecial.get(i).getTitle() %></a></h2>
				<p>
					<%
						if(ourspecial.get(i).getUrl().length()>128)
							out.println(ourspecial.get(i).getUrl().substring(0, 125));
						else
							out.println(ourspecial.get(i).getUrl());
					%>
				</p>
				<p><%=ourspecial.get(i).getDescription() %></p>
			</div>
		</div>
		<%
			}
		%>
		<div class="row" id="buttons">
			<button onclick="undo()">Previous Page</button>
			<button onclick="edit()">Next Page</button>
		</div>
	</div>	
	<hr>
	<div class="container">
		<footer>C&#149;E&#149;I&#149;R Google can't beat this</footer>
	</div>

	<script src="js/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
	<script type="text/javascript">
		var pN = 0;
		function edit(){
			pN += 1;
			nexta(pN);
		}
		function undo(){
			pN -= 1;
			nexta(pN);
		}
		
		$("#-1").hide();
		$("#-4").hide();
		$("#-7").hide();
		$("#-10").hide();
		for(var i = 0; i < $(".result").length; i++){
			var ww = "#" + i;
			$(ww).hide();
		}
		
		function nexta(currPage) {
			if(currPage==0){
				$("#-1").show();
				$("#-4").show();
				$("#-7").show();
				$("#-10").show();
	
				for(var i = 0; i < 10; i++){
					var ww = "#" + i;
					$(ww).show();
				}
			}
			else{
				$("#-1").hide();
				$("#-4").hide();
				$("#-7").hide();
				$("#-10").hide();
				console.log(currPage*10+10);
				
				for(var i = 0; i < $(".result").length; i++){
					var ww = "#" + i;
					var temp = currPage*10+10;
					
					if(i>currPage*10 && i<temp)
						$(ww).show();
					else
						$(ww).hide();
				}
			}
		}
		nexta(pN);
	</script>
</body>
</html>