<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    body {
        background-image: url("https://cdn.dribbble.com/users/2297452/screenshots/5289768/comp_1_1.gif");

        background-position: center;
        -moz-background-size: 80%;
        -webkit-background-size: 80%;
        -o-background-size: 80%;
        background-size: 80%;
    }
</style>
<head>
    <title>Wait</title>
</head>
<body>
<div class="block">
    <div align="center">
        <form action="/fs/wait/" method="post">
            <input type="hidden" name="bat" value="in"/>
            <input type="submit" value="Refresh"/>
        </form>
    </div>
    <div align="center" class="button">
        <form action="/fs/wait/" method="post">
            <input type="hidden" name="bat" value="out"/>
            <input type="submit" value="Leave"/>
        </form>
    </div>
</div>
</body>
</html>
