<!DOCTYPE html>
<?php
session_start();
$_SESSION['codeID']=0;
$cookie_name = "clasCook";
$cookie_value = 0;
setcookie($cookie_name, $cookie_value, time() + (86400 * 30), "/"); // 86400 = 1 day
?>


<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width">
    <meta name="keywords" content="Ty Hamilton's Portfolio">
    <meta name="keywords" content="Java, SQL, Android">
    <meta name="author" content="Tyler Hamilton">
    <title>Ty Hamilton info | Main Page</title>
    <link rel="stylesheet" href="http://tyhamilton.info/style_ty1.css">

    <?php
     require 'com.php';
     require 'cla.php';
    ?>

    <script language = "javscript" type="text/javascript">
    <!-- Hide JavaScript loading data
    function phpPull(numCP){
      var el = document.getElementById('blog');
      // var cooks = getCookie("clasCook");
      document.cookie = "clasCook="+numCP;
      var path = 'index.php?cl='+numCP;
      // window.location.href = path;
      el.innerHTML = <?php searchCode(); ?>;


    }

    -->

    </script>
    <noscript>
      <h2>Please enable JavaScript to view this site</h2>
    </noscript>
  </head>
  <body onload="launcherS();">
    <header>
      <div class="container">
        <div id="ty">
          <h1> TyHamilton.info</h1>
        </div>

      </div>
    </div>
    </header>

    <section id="intro">
      <div class="container">
        <h1> Software Developer Portfolio</h1>
        <p>This site is created from HTML, JavaScript, PHP and SQL</p>
      </div>
    </section>

    <section id="navP">
    </section>
    <section id="navF">
    </section>

    <section id="blog">

    </section>



<footer>
  <p>TyHamilton.info , Copyright &copy; 2018</p>
</footer>


  </body>
</html>
