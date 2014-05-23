<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>
</head>
<body>
<div class="jumbotron">
    <h1>Welcome!</h1>
    <p>This is Schmitty: <g:img dir="images" file="schmitty.png" style="border: 1px solid black;" /></p>
    <p>Schmitty loves ice cream. Schmitty likes to keep a log of all the ice cream he eats, using this grails application.</p>
    <p>
       Schmitty also likes to use Fit Bit!
       He asked us to come up with a way to allow him to track his ice cream consumption both in his grails application and in his fitbit profile!
       We created a custom integration with Fit Bit, allowing him to authenticate his grails application against his Fit Bit identity. When he logs the
       ice cream he eats, the application also uses the Fit Bit JSON REST API to post his activity to his Fit Bit profile!
    </p>

    <p><h3>Login to Schmittys account with:</h3>
<h4>user: jwickard+schmidt@gmail.com</h4>
<h4>password: fitbit</h4></p>

    <p><oauth:connect provider="fitbit" id="fitbit-connect-link" class="btn btn-primary btn-lg">Help Schmitty Log Some Ice Cream!</oauth:connect></p>
</div>
</body>
</html>
