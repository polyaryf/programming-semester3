<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
	<title>Slide Navbar</title>
	<link rel="stylesheet" type="text/css" href="/static/css/styleHeader.css">
	<link rel="stylesheet" type="text/css" href="/static/css/styleLessons.css">
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
	<article><a href="/lessons">Lessons</a></article>
	<article><a href="/favourite-lessons">Favourites</a></article>
</section>

<main class="main">

    <#if lessonDtoList?has_content>
    	<#list lessonDtoList as lesson>
			<div class="card">
				<div class="title">
				<h1>${lesson.title}</h1>
					<p>${lesson.description}</p>
				</div>
				<a href="/files/${lesson.fileId}">download file</a>
				<a href="${lesson.videoLink}" class="video"> <h5> watch the video</h5> </a>

				<button class="button button-like" value="${lesson.id}">
					<i class="fa fa-heart"></i>
					<span>Like</span>
				</button>

			</div>
        </#list>
    </#if>

</main>

<footer></footer>

<script>

	$('button.button-like').on('click', function(event) {
		$(this).toggleClass("liked");

        setTimeout(() => {
            $(event.target).removeClass('liked')
        }, 1000)

        $.ajax({
			type: "POST",
			url: "/lessons",
			data: $(this).val().toString(),
			success: function (result) {
				console.log(result);
			},
			dataType: "text/plain"
		});
	});

    function doSignOut(url){
        document.location.replace(url)
    }

</script>

</body>
</html>