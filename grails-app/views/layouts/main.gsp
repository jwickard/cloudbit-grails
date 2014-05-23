<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title><g:layoutTitle default="Grails"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Le styles -->
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="${resource(dir:'js', file: 'html5shiv.min.js')}" type="text/javascript"></script>
    <![endif]-->

    <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">

    <!-- Fav and touch icons -->
    <link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
    <link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">

    <g:layoutHead/>
    <g:javascript library="application"/>
    <r:layoutResources />
</head>

<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Grails Cloud-Bit</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><g:link controller="welcome" action="index">Home</g:link></li>
                <sec:ifNotLoggedIn>
                    <li><oauth:connect provider="fitbit" id="fitbit-connect-link">Login</oauth:connect></li>
                </sec:ifNotLoggedIn>
                <sec:ifLoggedIn>
                    <li><g:link controller="iceCreamEntry" action="index"><g:message code="icecream.nav.link.name" /></g:link></li>
                    <li><g:link controller="flavor" action="index"><g:message code="flavor.nav.link.name" /></g:link></li>
                </sec:ifLoggedIn>
            </ul>
            <sec:ifLoggedIn>
                <ul class="nav navbar-nav navbar-right">
                    <li><li class="pull-right"><a href="#">Welcome: <sec:username/></a></li></li>
                </ul>
            </sec:ifLoggedIn>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<div class="container">

    <g:layoutBody/>

</div> <!-- /container -->
<r:layoutResources />
</body>
</html>

