<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    <%@include file="main_css.css"%>
</style>
<head>
    <title>Finish</title>
</head>
<body>
<div id="he">
    <div id="hero2">
        <nav>
            <div align='center' class="topnav">
                <h2>Login : ${b.player2.login} </h2>
                <h2>Level : ${b.player2.lvl} </h2>
                <h2>Class : ${b.player2.clas}</h2>
                <h2>Stars : ${b.player2.stars}</h2>
                <h2>Points : ${b.player2.points}</h2>
            </div>
        </nav>
    </div>
</div>
<div>
    <h2>You earned ${b.battlePointsPlayer2} points</h2>
</div>
<hr/>
<div align='center' class="topnav">
<c:if test="${b.hp1<=0}">
    ${b.player2.login} WIN!
</c:if>
</div>
<form action="/fs/finish" method="post">
    <input type="submit" value="Exit"/>
</form>
    <div align='center' class="topnav">
<c:if test="${b.hp2<=0}">
    ${b.player1.login} WIN!
</c:if>
    </div>
<hr/>
<div>
    <h2>You earned ${b.battlePointsPlayer1} points</h2>
</div>
<div id="hero1">
    <nav>
        <div align='center' class="topnav">
            <h2>Login : ${b.player1.login} </h2>
            <h2>Level : ${b.player1.lvl} </h2>
            <h2>Class : ${b.player1.clas}</h2>
            <h2>Stars : ${b.player1.stars}</h2>
            <h2>Points : ${b.player1.points}</h2>
        </div>
    </nav>
</div>
</body>
</html>