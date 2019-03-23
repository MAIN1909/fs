<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Deck Maker</title>
    <style>
        body {
            background: #b4b4b4;
        }
        table, th, td {
            border: 2px solid black;
            border-collapse: collapse;
        }

        th, td {
            padding: 5px;
            text-align: left;
        }

        nav {
            text-align: center;
        }

        h2 {
            background-color: #5C8CA7;
            display: inline;
            border: 1px solid #000;
            padding: 13px;
            margin: 10px;

        }

        .item img {
            width: 40px;
            height: 40px;
        }
    </style>
</head>
<body>
<nav>

    <h2>Login : ${u.login} </h2>
    <h2>Level : ${u.lvl} </h2>
    <h2>Points : ${u.points}</h2>
    <h2>Stars : ${u.stars}</h2>

</nav>

<div>
    <table style="width: 100%">
        <caption>Deck</caption>
        <tr>
            <td style="width: 50%">Selected cards</td>
            <td>All cards</td>
        </tr>
        <tr>
            <td id="userCards">
                <table style="width: 240px">

                    <c:set var="counter" value="${1}"/>
                    <c:forEach items="${userCards}" var="i">
                        <c:set var="counter" value="${counter+1}"/>
                        <c:if test="${counter%2==0}">
                            <tr>
                            <td style="width: 100px">

                                <form action="/fs/deck?id=-${i.id}" method="post">
                                    <div class="item">
                                            ${i.name}<br/>
                                        <h3>${i.name} Cost:${i.cost}</h3>
                                        <img src='${i.pic}' alt='card'/>
                                        <h3>${i.damage} ${i.armor}</h3>
                                    </div>
                                    <input type="submit" value="Remove"/>
                                </form>

                            </td>
                            <td>
                        </c:if>
                        <c:if test="${counter%2!=0}">
                            <form action="/fs/deck?id=-${i.id}" method="post">
                                <div class="item">
                                        ${i.name}<br/>
                                    <h3>${i.name} Cost:${i.cost}</h3>
                                    <img src='${i.pic}' alt='card'/>
                                    <h3>${i.damage} ${i.armor}</h3>
                                </div>
                                <input type="submit" value="Remove"/>

                            </form>

                            </td>
                            </tr>
                        </c:if>
                    </c:forEach>

                </table>
            </td>


            <td id="allCards">

                <table style="width: 240px">

                    <c:set var="counter" value="${1}"/>
                    <c:forEach items="${cards}" var="i">
                        <c:set var="counter" value="${counter+1}"/>
                    <c:if test="${counter%2==0}">
                    <tr>
                        <td style="width: 100px">
                            <form action="/fs/deck?id=${i.id}" method="post">
                                <div class="item">
                                        ${i.name}<br/>
                                    <h3>${i.name} Cost:${i.cost}</h3>
                                    <img src='${i.pic}' alt='card'/>
                                    <h3>${i.damage} ${i.armor}</h3>
                                </div>
                                <input type="submit" value="Add"/>
                            </form>
                        </td>
                        <td>
                            </c:if>

                            <c:if test="${counter%2!=0}">
                            <form action="/fs/deck?id=${i.id}" method="post">

                                <div class="item">
                                        ${i.name}<br/>
                                    <h3>${i.name} Cost:${i.cost}</h3>
                                    <img src='${i.pic}' alt='card'/>
                                    <h3>${i.damage} ${i.armor}</h3>
                                </div>
                                <input type="submit" value="Add"/>
                            </form>
                        </td>
                    </tr>

                    </c:if>
</div>
</c:forEach>
</table>
</td>

</tr>
</table>
</div>
<div align='center'>
    <form action="/fs/deck?id=0" method="post">
        <input type="submit" value="Save Deck"/>
    </form>
</div>
<div align='center'>
    <form action="/fs/main/" method="get">
        <input type="submit" value="Main Page"/>
    </form>
</div>
</body>
</html>