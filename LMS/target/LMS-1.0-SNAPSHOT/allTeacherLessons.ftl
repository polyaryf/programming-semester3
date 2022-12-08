<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
	<title>All teacher's lessons</title>
	<link rel="stylesheet" type="text/css" href="/static/css/styleHeader.css">
	<link rel="stylesheet" type="text/css" href="/static/css/styleAllLessons.css">
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
	<article><a href="/profile" class="black">Profile</a></article>
	<article><a href="/subscribers" class="black">Subscribers</a></article>
	<article><a href="/my-lessons" class="black">My lessons</a></article>
</section>

<article class="create-lesson" ><a href="/create-lesson" class="create-lesson">Create lesson</a></article>

<main class="main">

	<#if lessonDtoList?has_content>
		<#list lessonDtoList as lesson>
			<div class="card">
				<div class="title">
					<h3>${lesson.title}</h3>
				</div>
				<p>${lesson.description}</p>
				<a href="/files/${lesson.fileId}" class="file" >download file</a>
				<h5><a href="${lesson.videoLink}" class="video"> watch the video</a></h5>
			</div>
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