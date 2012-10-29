<html>
<head>
    <style type="text/css">
        body {
            height: 100%;
        }

        body > table {
            width: 100%;
            height: 100%;
        }

        body > table > tbody > tr > td
        {
            text-align: center;
        }

        form > table
        {
            margin-left:auto;
            margin-right:auto;
        }

        .error
        {
            font-weight: bold;
            color: red;
        }
    </style>
</head>
<body>
<table>
    <tr>
        <td>
            <h1>Apache Shiro Example</h1>
            <form method="post" action="">
                <table>
                    <tr>
                        <td>Username</td>
                        <td><input type="text" name="username"/></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="password"/></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><input type="submit" value="Login"></td>
                    </tr>
                </table>
            </form>

            <#if userNotFound?? && userNotFound?string == "true">
                <div class="error">User not found</div>
            <#elseif userLocked?? && userLocked?string == "true">
                <div class="error">User locked</div>
            <#elseif error?? && error?string == "true">
                <div class="error">Internal error</div>
            </#if>

            <span>
                Roles: "user". username: user, password: user<br>
                Roles: "admin", "user". username: admin, password: admin<br>
            </span>

        </td>
    </tr>
</table>
</body>
</html>