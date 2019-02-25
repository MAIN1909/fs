<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    <%@ include file="main_css.css"%>
</style>
<head>
    <title>Main</title>
</head>
<br>
<div>
    <table>
        <tr>
            <td>Level</td>
            <td>Points</td>
            <td>Stars</td>
        </tr>
    </table>


</div>
<div class="container">
    <c:forEach items="${items}" var="i">
        <div class="item">

            <h2>${i.name}</h2></p>
            <h3>${i.cost}</h3>
            <h3>${i.pic}</h3><br/>
            <br/>
        </div>
    </c:forEach>
</div>
<div>
    <form>
        <input type="submit" value="Form Deck">

    </form>
</div>

</body>
</html>
