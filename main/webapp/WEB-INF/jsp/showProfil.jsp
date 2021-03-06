<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="robots" content="noindex, nofollow"/>
    <title>Randonneur</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <style>
        .logout {
        background: none !important;

        color: inherit;

        border: none;

        margin: 15px 10px 0 0;

        font: inherit;

        cursor: pointer;
        }

        body.image {
            background: url('/hiking/images/rest.jpeg') no-repeat !important;
        }
    </style>
</head>
<body class="image">
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <ul class="nav navbar-nav navbar-left">
            <li class="active"><a href="/hiking">Accueil</a></li>
            <li><a href="/hiking/showProfil/${user.id}">Mon Profil</a></li>
            <li><a href="/hiking/showHikes/${user.id}">Randonnées à faire</a></li>
            <li><a href="/hiking/doneHikes/${user.id}">Randonnées faites</a></li>
            <li><a href="/hiking/addHike/${user.id}">Ajouter une randonnée</a></li>

        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><c:url var="logoutUrl" value="/logout"/>
                <form  action="${logoutUrl}" method="post">
                    <input class="logout" type="submit" value="Se déconnecter" />
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </li>
        </ul>
    </div>
</nav>

<div class="container">
    <h3>Votre nom:  <c:out value="${user.username}"/></h3>
    <div class="panel panel-default">
        <div class="panel-body">

            <p> Nombre de randonnées faites: <c:out value="${nbrDoneHikes}"/> </p>
            <p>
                Nombre de randonnées à faire: <c:out value="${nbrHikeToDo}"/>
            </p>
            <p>
                Nombre de kilomètres parcourus: <c:out value="${nbrKmsWalked}"/>
            </p>
        </div>
    </div>

</div>
</body>
</html>
