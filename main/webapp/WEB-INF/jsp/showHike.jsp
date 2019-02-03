<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: xxx
  Date: 15/11/18
  Time: 20:24
  To change this template use File | Settings | File Templates.
--%>
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
    <div class="row">
    <h3 class="col-xs-6">
       Nom: <c:out value="${hike.name}"/>
    </h3>
        <div class="btn-group col-xs-6" style="margin-top: 22px;">
    <form method="get" action="/hiking/modifyHike/${hike.id}/${hike.user_id}" style="display:inline-block; margin-left: 5px;" class="pull-right">
        <button type="submit" class="btn btn-primary mb-2" >Modifier</button>
    </form>
<c:if test="${hike.done}">
    <form method="get" action="/hiking/changeDetails/${hike.id}" style="display:inline-block" class="pull-right">
        <button type="submit" class="btn btn-primary mb-2" >Changer image et description</button>
    </form>

</c:if>
        </div>
    </div>
<c:choose>

    <c:when test="${param.message==4}">
        <p>  La randoonée a bien été modifiée  </p>
    </c:when>

</c:choose>

    <c:if test="${hike.image != null}">
        <img src="/hiking/getImage/${hike.user_id}/${hike.image}" class="img-thumbnail img-responsive"  alt="myImage">
    </c:if>


    <div class="panel panel-default">
        <div class="panel-body">

      <table class="table ">

          <tr>
              <td>Distance: <c:out value="${hike.distance}"/></td>
          </tr>
          <tr>
              <td>Point de départ: <c:out value="${hike.starting_point}"/></td>
          </tr>
          <tr>
              <td>Point d'arrivée: <c:out value="${hike.ending_point}"/></td>
          </tr>
          <tr>
              <td>Durée: <c:out value="${hike.duration}"/></td>
          </tr>
          <tr>
              <td>Difficulté: <c:out value="${hike.difficulty}"/> </td>
          </tr>
          <c:if test="${hike.rating != null}">
              <tr>
                  <td>Note: <c:out value="${hike.rating}"/></td>
              </tr>
          </c:if>
          <c:if test="${hike.description != null}">
              <tr>
                  <td>Description: <c:out value="${hike.description}"/> </td>
              </tr>
          </c:if>

      </table>
            </div>
    </div>

</div>
</body>
</html>