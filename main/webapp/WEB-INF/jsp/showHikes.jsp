<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="robots" content="noindex, nofollow"/>
    <title>Randonneur</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <!-- CSS -->
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">

    <!-- CSS -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP" crossorigin="anonymous">

    <!-- JS -->
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>

    <style>
        table, th, td {
            border: 1px solid black;
        }
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
<h1>Randonnées à faire</h1>

<c:choose>
    <c:when test="${param.message==1}">
        <p>  La randoonée n'a pas été validée  </p>
    </c:when>
    <c:when test="${param.message==2}">
        <p>  La randoonée a bien été validée  </p>
    </c:when>
    <c:when test="${param.message==4}">
        <p>  La randoonée a bien été modifiée  </p>
    </c:when>

</c:choose>
<table id="hikes" style="width:100%" class="display table table-striped table-responsive">

    <thead>
    <tr>
        <th align="center" scope="col">Nom</th>
        <th align="center" scope="col" >Distance</th>
        <th align="center" scope="col" >Point de départ</th>
        <th align="center" scope="col">Point d'arrivée</th>
        <th align="center" scope="col">Durée</th>
        <th align="center" scope="col">Difficulté</th>
        <th align="center" scope="col">Marquer comme 'fait' et </br> ajouter le rating</th>
        <th align="center" scope="col">Montrer les détails</th>
        <th align="center" scope="col">Modifier</th>
        <th align="center" scope="col">Supprimer</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="hike" items="${hikes}">


        <tr>
            <td align="center">  <c:out value="${hike.name}"/></td>
            <td align="center">  <c:out value="${hike.distance}"/></td>
            <td align="center">  <c:out value="${hike.starting_point}"/></td>
            <td align="center">  <c:out value="${hike.ending_point}"/></td>
            <td align="center">  <c:out value="${hike.duration}"/></td>
            <td align="center">  <c:out value="${hike.difficulty}"/> </td>

            <td align="center">
                <a data-userid="${hike.user_id}" data-id="${hike.id}" class="my-modal" data-toggle="modal" data-target="#myModal"><i class="far fa-check-square"></i></a>
            </td>

            <td align="center"><a href="/hiking/showHike/${hike.id}"><i class="fas fa-search"></i></a> </td>

            <td align="center"> <a href="/hiking/modifyHike/${hike.id}/${hike.user_id}" ><i class="far fa-edit"></i></a>  </td>

            <td align="center"> <a href="/hiking/deleteHike/${hike.id}/${hike.user_id}/showHikes" ><i class="far fa-trash-alt"></i></a>  </td>

        </tr>


    </c:forEach>
    </tbody>

</table>

<!-- Trigger the modal with a button -->

<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Choissisez une note</h4>
            </div>
            <div class="modal-body">

                <a class="btn btn-info" href="" > 1</a>
                <a class="btn btn-info" href="" > 2 </a>
                <a class="btn btn-info" href="" > 3 </a>
                <a class="btn btn-info" href="" > 4 </a>
                <a class="btn btn-info" href="" > 5</a>

                <a class="btn checker" href="" > Valider sans donner le rating</a>
            </div>
            <div class="modal-footer">
                <button  type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
            </div>
        </div>

    </div>
</div>
</div>
</body>
</html>

<script>
    $(document).ready( function () {
        $('#hikes')
            .dataTable( {
                responsive: true

            } );
    } );

    $('.my-modal').on('click', function () {
        var hike_id = $(this).attr("data-id");
        var user_id = $(this).attr("data-userid");
        var rating = 1;

        var href_checker = "/hiking/checkHike/" + hike_id + "/" + user_id;
        $("div.modal-body a.btn.checker").attr("href",href_checker);

        $("div.modal-body a.btn-info").each(function() {

            var $this = $(this);
            //  var _href = $this.attr("href");
            var _href = "/hiking/setRating/";
            $this.attr("href", _href + rating +'/'+user_id + '/' +hike_id + '/showHikes');
            rating++;
        });
    })
</script>
