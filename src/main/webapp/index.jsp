<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Vehicle Management</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url('images/Background-image.png');
                        background-size: cover;
                        background-position: center;
                        background-repeat: no-repeat;
                        background-attachment: fixed;
                        display: flex;
                        justify-content: center;
                        align-items: center;
                        height: 100vh;
                        margin: 0;
        }
        .login-box {
            background: rgba(0, 0, 0, 0.5);
            padding: 30px;
            border-radius: 20px;
            width: 350px;
        }
        h2 { text-align: center; color: #333; }
        input {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        button {
            width: 100%;
            padding: 10px;
            background: #667eea;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .register-link {
            text-align: center;
            margin-top: 15px;
            color: #FFD700;
            text-shadow: 1px 1px 2px black;
            font-weight: bold;
        }
        .error {
            color: red;
            text-align: center;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div class="login-box">
        <h2> SLIIT vehicle park</h2>

        <% if(request.getParameter("msg") != null) { %>
            <div style="color: green; text-align: center;">
                <%= request.getParameter("msg") %>
            </div>
        <% } %>

        <% if(request.getAttribute("error") != null) { %>
            <div class="error">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>

        <form action="login" method="post">
            <input type="text" name="userId" placeholder="User ID" required>
            <input type="password" name="password" placeholder="Password" required>
            <button type="submit">Login</button>
        </form>

        <div class="register-link">
            <a href="register.jsp">New User? Register Here</a>
        </div>
    </div>
</body>
</html>
