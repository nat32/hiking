<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
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
            <li><a href="/hiking/showProfil/${hike.user_id}">Mon Profil</a></li>
            <li><a href="/hiking/showHikes/${hike.user_id}">Randonnées à faire</a></li>
            <li><a href="/hiking/doneHikes/${hike.user_id}">Randonnées faites</a></li>
            <li><a href="/hiking/addHike/${hike.user_id}">Ajouter une randonnée</a></li>

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
<h1>Changer image et description</h1>
    <c:choose>
        <c:when test="${param.msg==1}">
            <p> Taille maximum du fichier autorisée est 3 MB, veuillez réessayer svp  </p>
        </c:when>
        <c:when test="${param.msg==2}">
            <p>  Le type du fichier n'est pas autorisé, veuillez réessayer svp  </p>
        </c:when>
        <c:when test="${param.msg==3}">
            <p>  Il y a eu une erreur, veuillez réessayer svp </p>
        </c:when>

    </c:choose>

<form method="POST" action="/hiking/changeDetailsPost?_csrf=${_csrf.token}" enctype="multipart/form-data">
    <div class="form-group row">
        <c:choose>
            <c:when test="${hike.image != null}">
                <img src="/hiking/getImage/${hike.user_id}/${hike.image}" class="img-thumbnail img-responsive"  alt="myImage">
            </br>
                <p>Changer l'image</p>
            </c:when>
            <c:otherwise>
                <p>Ajouter une image</p>
            </c:otherwise>
        </c:choose>

     <input type="file" name="file" class="btn btn-primary mb-2">

    </div>
    <div class="form-group row">
        <label for="description">Description:</label>
        <textarea class="form-control" rows="7" cols="70" name="description"  id="description">
        <c:out value="${hike.description}"/>
    </textarea>


    </div>
    <input type="hidden"  name="user_id" value="${hike.user_id}" />
    <input type="hidden"  name="hike_id" value="${hike.id}" />
    <div class="form-group row">
    <input type="submit" value="Envoyer" class="btn btn-primary mb-2">
    </div>
</form>
</div>
</body>
</html>
