<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<style>
    <%@include file='register_css.css' %>

</style>
<head>
    <title>Login</title>
</head>
<body>
<div id="registration-form">
    <div class='fieldset'>
        <legend>Welcome to FightStone</legend>
        <form action="/fs/" method="POST">
            <div class='row'>
                <label for='login'>Login</label>
                <input type="text" placeholder="login" name='login' id='login' data-required="true"
                       data-error-message="Your login is required">
            </div>
            <div class='row'>
                <%--@declare id="pass"--%><label for='pass'>Password</label>
                <input type="password" placeholder="password" name='pass' data-required="true" data-type="password"
                       data-error-message="Your password is required">
            </div>

            <input type="submit" value="Sign In">

        </form>
        <hr/>
        <form action="/fs/register/" method="GET">
            <input type="submit" value="Register"/>
        </form>

    </div>
</div>
</form>
</body>
</html>

