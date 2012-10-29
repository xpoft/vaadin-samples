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
            <h1>Spring Security Example</h1>
            <form method="post" action="/j_spring_security_check">
                <table>
                    <tr>
                        <td>Username</td>
                        <td><input type="text" name="j_username"/></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="j_password"/></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><input type="submit" value="Login"></td>
                    </tr>
                </table>
            </form>

            <#if isError?? && isError?string == "true">
                <div class="error">User not found</div>
            </#if>

            <span>
                ROLE_USER. username: user, password: user<br>
                ROLE_ADMIN, ROLE_USER. username: admin, password: admin<br>
            </span>

        </td>
    </tr>
</table>
</body>
</html>