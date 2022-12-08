<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
	<title>Slide Navbar</title>
	<link rel="stylesheet" type="text/css" href="/static/css/styleHeader.css">
	<link rel="stylesheet" type="text/css" href="/static/css/styleFavourites.css">
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

    <#if favouriteLessonsList?has_content>
    <#list favouriteLessonsList as lesson>
	<div class="card" id="${lesson.id}">
		<button class="btn btn-delete" value="${lesson.id}">
			<span class="mdi mdi-delete mdi-24px"></span>
			<span class="mdi mdi-delete-empty mdi-24px"></span>
			<span>Delete</span>
		</button>
		<h2>${lesson.title}</h2>
		<h5>${lesson.description} </h5>

	</div>
	</#list>
	</#if>

</main>

<footer></footer>

<script>
    function doSignOut(url){
        document.location.replace(url)
    }

    $('.btn-delete').on('click', function(event) {
        let id = $(this).val().toString()

        $.ajax({
            type: "POST",
            url: "/favourite-lessons",
            data: id,
        }).done(function (result) {
            console.log(result);
            $('#' + id).hide(300)
        }).fail(function (result){
            console.error(result)
		});
    });

</script>

</body>
</html>