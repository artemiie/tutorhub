<#ftl encoding="UTF-8">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Thank you for registration ${recipientName}</title>
    <style>
        #body {
            display: flex;
            flex-direction: column;
            font-family: "Segoe UI", serif;
            align-items: center;
            background-color: #607FF2;
            font-size: 20px;
            height: 100%;
        }

        #div {
            display: block;
            flex-direction: column;
            margin: 10%;
            font-family: "Segoe UI", serif;
            background-color: white;
            padding: 3%;
            border-radius: 20px;
            color: black;
        }

    </style>
</head>
<body>
<div id="body">
    <div id="div">
        <h1 style="text-align: center">Registration</h1>
        <p>Hello, ${recipientName}!</p>
        <p><a href="http://localhost:8080/api/v1/auth/confirm?token=${activationToken}">Confirm your registration</a></p>
        <p>This is registration mail. You can log in and use tutorhub.</p>
    </div>
</div>
</body>
</html>