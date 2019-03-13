<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    <%@include file="main_css.css"%>
    body {
        background-image: url("https://s3.amazonaws.com/user-media.venngage.com/299799-53374269481033ba23118a23eb394d20.jpg");

        background-position: center;
        -moz-background-size: 96%;
        -webkit-background-size: 96%;
        -o-background-size: 96%;
        background-size: 96%;
    }
</style>
<head>
    <title>Battle</title>
</head>
<body>
<div id="he">
    <div id="hero2">
        <nav>
            <div align='center' class="topnav">
                <h2>Login : ${b.player2.login} </h2>
                <h2>Level : ${b.player2.lvl} </h2>
                <h2>Class : ${b.player2.clas}</h2>
                <h2>HP : ${b.hp2}</h2>
                <c:if test="${b.heroPowered1}">
                    <form action="/fs/battle" method="post" class="power">
                        <input type="hidden" name="attackHero" value="true"/>
                        <input type="submit" value="Attack Hero"/>
                    </form>
                </c:if>
                <h2>MANA : ${b.mana2}</h2>
            </div>
        </nav>
    </div>
    <hr/>
    <div align='left' class="container" id="deck_hand2">
        <h2>Deck size : ${b.deck2.size()}</h2>
        <h2>Hand size : ${b.inHand2.size()}</h2>
    </div>
    <hr/>
    <div align='right' class="container" id="table2">
        <c:forEach items="${b.onTable2}" var="i">
            <div class="item">
                <h3>${i.name} Cost:${i.cost}</h3>
                <img src='${i.pic}' alt='card'/>
                <h3>${i.damage} ${i.armor}</h3>
                <c:if test="${b.fromTableChoosen != null}">
                    <form action="/fs/battle" method="post">
                        <input type="hidden" name="attack" value="${i.id}"/>
                        <input type="submit" value="Attack"/>
                    </form>
                </c:if>
            </div>
        </c:forEach>
    </div>
</div>
<hr/>
<c:if test="${b.move1 && b.fromHandChoosen == null && b.fromTableChoosen == null}">
    <form action="/fs/battle" method="post">
        <input type="hidden" name="endTurn" value="true"/>
        <input type="submit" value="End Turn"/>
    </form>
</c:if>
<c:if test="${!b.move1}">
    <form action="/fs/battle" method="get">
        <input type="submit" value="Refresh"/>
    </form>
</c:if>
<h3>Turn ${b.numberOfMove}</h3>
<hr/>
<div id="me">
    <div align='right' class="container" id="table1">
        <c:forEach items="${b.onTable1}" var="i">
            <div class="item">
                <h3>${i.name} Cost:${i.cost}</h3>
                <img src='${i.pic}' alt='card'/>
                <h3>${i.damage} ${i.armor}</h3>
                <c:if test="${b.move1 && i.damage>0 && i.cardCanMoove && b.fromHandChoosen == null && b.fromTableChoosen == null}">
                    <form action="/fs/battle" method="post">
                        <input type="hidden" name="table" value="${i.id}"/>
                        <input type="submit" value="Choose"/>
                    </form>
                </c:if>
                <c:if test="${b.fromTableChoosen == i.id}">
                    <form action="/fs/battle" method="post">
                        <input type="hidden" name="table" value="${-i.id}"/>
                        <input type="submit" value="Unchoose"/>
                    </form>
                </c:if>
            </div>
        </c:forEach>
    </div>
    <hr/>
    <div align='left' class="container" id="deck_hand1">
        <h2>Deck size : ${b.deck1.size()}</h2>
        <c:if test="${b.mana1>=2 && !b.heroPowered1}">
            <form action="/fs/battle" method="post" class="power">
                <input type="hidden" name="power" value="true"/>
                <input type="submit" value="Use Power"/>
            </form>
        </c:if>
        <h2>Hand:</h2>
        <c:forEach items="${b.inHand1}" var="i">
            <div class="item">
                <h3>${i.name} Cost:${i.cost}</h3>
                <img src='${i.pic}' alt='card'/>
                <h3>${i.damage} ${i.armor}</h3>
                <c:if test="${b.move1 && i.cost <= b.mana1 && b.fromHandChoosen == null && b.fromTableChoosen == null}">
                    <form action="/fs/battle" method="post">
                        <input type="hidden" name="hand" value="${i.id}"/>
                        <input type="submit" value="Choose"/>
                    </form>
                </c:if>
                <c:if test="${b.fromHandChoosen == i.id}">
                    <form action="/fs/battle" method="post">
                        <input type="hidden" name="hand" value="${i.id}"/>
                        <input type="submit" value="Unchoose"/>
                    </form>
                </c:if>
            </div>
        </c:forEach>
    </div>
    <hr/>
    <div id="hero1">
        <nav>
            <div align='center' class="topnav">
                <h2>Login : ${b.player1.login} </h2>
                <h2>Level : ${b.player1.lvl} </h2>
                <h2>Class : ${b.player1.clas}</h2>
                <h2>HP : ${b.hp1}</h2>
                <h2>MANA : ${b.mana1}</h2>
            </div>
        </nav>
    </div>
</div>
</body>
</html>