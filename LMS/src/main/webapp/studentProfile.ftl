<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
	<title>Slide Navbar</title>
	<link rel="stylesheet" type="text/css" href="/static/css/styleHeader.css">
	<link rel="stylesheet" type="text/css" href="/static/css/styleStudentProfile.css">
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
<main>
    <#if studentProfile?has_content>
	<div class="card">
		<div id="pic">

        <#if (studentProfile.photoId)??>
			<img class="avatar" width="600px" src="/files/${studentProfile.photoId}"/>
        <#else>
			<img width="600px" src="https://img.freepik.com/free-vector/teacher-standing-near-blackboard-holding-stick-isolated-flat-vector-illustration-cartoon-woman-character-near-chalkboard-pointing-alphabet_74855-8600.jpg" >
        </#if>

		</div>
		<h1> ${studentProfile.firstName}</h1>
		<h1>${studentProfile.lastName}</h1>
		<p class="title">Status: ${user.status}</p>

        <#if (studentProfile.tgUsername)??>
		<a href="https://t.me/${studentProfile.tgUsername}"><i class="fa fa-telegram"></i></a>
		<#else>
		<a href="#"><i class="fa fa-telegram"></i></a>
		</#if>

		<p><a class="button" href="/profile-student-edit" methods="Get">Edit profile</a></p>
	</div>
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