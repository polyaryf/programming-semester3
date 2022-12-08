<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
	<title>Slide Navbar</title>
	<link rel="stylesheet" type="text/css" href="/static/css/styleHeader.css">
	<link rel="stylesheet" type="text/css" href="/static/css/styleSubscribers.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<body>
<div id="blurry-filter"></div>
<header>
	<div>
		<article id="title"><span class="name">study.practice.repeat.</span></article>
		<article id="reference">
			<a class="signOut" target="_blank" rel="noopener">
				<svg width="60" height="24" viewBox="0 0 60 60">
					<p onclick="doSignOut('/sign-out')">sign out</p>
				</svg>
			</a>
		</article>
	</div>
</header>
<section id="folders">
	<article><a href="/profile">Profile</a></article>
	<article><a href="/subscribers">Subscribers</a></article>
	<article><a href="/my-lessons">My lessons</a></article>
</section>
<main class="main">

    <#if subscribers?has_content>
    <#list subscribers as subscriber>
	<div class="card">
		<#if (subscriber.photoId)??>
		<div id="pic">
			<img width="400px" src="/files/${subscriber.photoId}">
		</div>
		<#else>
			<div id="pic">
				<img width="400px" src="https://img.freepik.com/free-vector/teacher-standing-near-blackboard-holding-stick-isolated-flat-vector-illustration-cartoon-woman-character-near-chalkboard-pointing-alphabet_74855-8600.jpg" >
			</div>
		</#if>
		<h1> ${subscriber.firstName}</h1>
		<h1> ${subscriber.lastName}</h1>
		<#if (subscriber.tgUsername)??>
		<a href="https://t.me/${subscriber.tgUsername}"><i class="fa fa-telegram"></i></a>
		<#else>
		<a href="https://t.me"><i class="fa fa-telegram"></i></a>
		</#if>
	</div>
	<br>
	</#list>
	</#if>

</main>

<footer></footer>

<script>
    function doSignOut(url){
        document.location.replace(url)
    }
</script>

</body>
</html>