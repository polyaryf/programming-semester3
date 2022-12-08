<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
	<title>Slide Navbar</title>
	<link rel="stylesheet" type="text/css" href="/static/css/styleHeader.css">
	<link rel="stylesheet" type="text/css" href="/static/css/styleEditTeacherProfile.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<body>
<div id="blurry-filter"></div>
<header>
	<div>
		<article id="title"><span class="name">study.practice.repeat.</span></article>
		<article id="reference">
			<a class="signOut" href="http://localhost:8080/welcome" target="_blank" rel="noopener">
				<svg width="60" height="24" viewBox="0 0 60 60">
					<p>sign out</p>
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
<main>

	<div class="card">
		<form id="edit-form" method="POST" action="/profile-student-edit" enctype="multipart/form-data">
			<h5 class="h5">Upload your photo:</h5>
			<input type="file" name="photoFile" required>
			<h5 class="h5">Telegram username:</h5>
			<input type="text" name="tgUsername" placeholder="Username" required>
			<button type="submit" class="save" >Save</button>
		</form>
	</div>

</main>

<footer></footer>


</body>
</html>