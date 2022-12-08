<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
	<title>Slide Navbar</title>
	<link rel="stylesheet" type="text/css" href="/static/css/styleHeader.css">
	<link rel="stylesheet" type="text/css" href="/static/css/styleTeacherProfile.css">
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

<main>
    <#if teacherProfile?has_content>
	<div class="card">
		<#if (teacherProfile.photoId)??>
			<div id="pic">
				<img width="600px" src="/files/${teacherProfile.photoId}" >
			</div>
		<#else>
		<div id="pic">
			<img width="600px" src="https://img.freepik.com/free-vector/teacher-standing-near-blackboard-holding-stick-isolated-flat-vector-illustration-cartoon-woman-character-near-chalkboard-pointing-alphabet_74855-8600.jpg" >
		</div>
		</#if>
		<h1> ${teacherProfile.firstName} </h1>
		<h1> ${teacherProfile.lastName} </h1>
		<p class="title">Status: ${teacherProfile.status}</p>

		<#if (teacherProfile.workExperience)??>
			<p class="title">Experience: ${teacherProfile.workExperience}</p>
		<#else>
			<p class="title">Experience: no info</p>
		</#if>


        <#if (teacherProfile.tgUsername)??>
			<a href="https://t.me/${teacherProfile.tgUsername!}"><i class="fa fa-telegram"></i></a>
        <#else>
			<a><i class="fa fa-telegram"></i></a>
		</#if>

		<p><a class="button" href="/profile-teacher-edit" methods="Get">Edit profile</a></p>
	</div>

	<div id="description">
        <#if (teacherProfile.description)??>
            ${teacherProfile.description}
        <#else>
			no info
		</#if>
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