<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
            <li><a href="/hiking/showProfil/${user_id}">Mon Profil</a></li>
            <li><a href="/hiking/showHikes/${user_id}">Randonnées à faire</a></li>
            <li><a href="/hiking/doneHikes/${user_id}">Randonnées faites</a></li>
            <li><a href="/hiking/addHike/${user_id}">Ajouter une randonnée</a></li>

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
<h2>Ajouter une randonnée</h2>
    <div>

        <c:if test="${param.name_error==1}">
            <p class="error"> Le nom est obligatoire et doit avoir maximum 255 caractères </p>
        </c:if>
        <c:if test="${param.distance_error==1}">
            <p class="error"> La distance est obligatoire et doit contenir une valeur entre 1 et 10000 </p>
        </c:if>
        <c:if test="${param.starting_point_error==1}">
            <p class="error">Le point de départ est obligatoire et doit avoir maximum 255 caractères</p>
        </c:if>
        <c:if test="${param.ending_point_error==1}">
            <p class="error">Le point d'arrivée est obligatoire et doit avoir maximum 255 caractères</p>
        </c:if>
        <c:if test="${param.duration_error==1}">
            <p class="error">La durée est obligatoire et doit contenir une valeur entre 1 et 10000</p>
        </c:if>


    </div>
<form:form  method="POST" action="/hiking/registerHike?_csrf=${_csrf.token}" modelAttribute="hike">
    <div class="form-group row">
        <label for="name" class="col-2 col-form-label">Nom *</label>
        <div class="col-10">
            <form:input class="form-control" path="name" type="text" value="${hike.name}" id="name"/>
        </div>
    </div>

    <div class="form-group row">
        <label for="distance" class="col-2 col-form-label">Distance *</label>
        <div class="col-10">
            <form:input class="form-control" path="distance" type="number" value="${hike.distance}"  step="0.01" id="distance" />
        </div>
    </div>

    <div class="form-group row">
        <label for="starting_point" class="col-2 col-form-label">Point de départ *</label>
        <div class="col-10">
            <form:input class="form-control" path="starting_point" type="text" value="${hike.starting_point}"  id="starting_point"/>
        </div>
    </div>
    <div class="form-group row">
        <label for="ending_point" class="col-2 col-form-label">Point d'arrivée *</label>
        <div class="col-10">
            <form:input class="form-control" path="ending_point" type="text" value="${hike.ending_point}" id="ending_point"/>
        </div>
    </div>
    <div class="form-group row">
        <label for="duration" class="col-2 col-form-label">Durée *</label>
        <div class="col-10">
            <form:input  class="form-control" path="duration" type="number" value="${hike.duration}" step="0.01" id="duration" />
        </div>
    </div>
    <div class="form-group row">
        <label for="difficulty">Difficulté</label>
        <form:select class="form-control" path="difficulty" id="difficulty" >
            <option value="facile">Facile</option>
            <option value="moyenne">Moyenne</option>
            <option value="difficile">Difficile</option>
        </form:select>
    </div>
    <button type="submit" class="btn btn-primary mb-2">Ajouter</button>
    <form:hidden path="user_id" value="${user_id}" />
</form:form>
</div>
</body>
</html>
