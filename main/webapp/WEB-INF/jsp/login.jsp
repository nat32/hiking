<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="robots" content="noindex, nofollow"/>
    <title>Randonneur</title>
    <style type="text/css">

        body.image {
            background: url('/hiking/images/login.jpeg') no-repeat !important;
        }
        .panel {
            padding : 10px;
        }


    </style>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body onload='document.f.username.focus();' class="image">
<div class="container" >

    <h3>Randonneur - Connexion</h3>

    <div class="col-lg-12">
        <div  class="col-md-6 col-xs-12">
            <div class="panel panel-default">
                <div class="panel-head">


                <c:if test="${not empty error}">
                    <div class="alert alert-warning" role="alert">
                        Il ya eu une erreur. Veuillez réessayer svp <br/>
                        ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                    </div>
                </c:if>

                </div>
                <div class="panel-body">
                    <form action="j_spring_security_check?_csrf=${_csrf.token}" name="f" method="post">
                        <div class="form-group row">
                            <label for="username" class="col-2 col-form-label">Nom</label>
                            <div class="col-10">
                                <input class="form-control" type="text" name="username" value="" id="username">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="password" class="col-2 col-form-label">Mot de passe</label>
                            <input type="password" class="form-control" id="password" type="password" value="" name="password" placeholder="Password">
                        </div>
                        <button type="submit" class="btn btn-primary mb-2" name="Submit" value="Submit">Envoyer</button>
                        <button type="reset" class="btn btn-primary mb-2" name="reset" >Réinitaliser</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
