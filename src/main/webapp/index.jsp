<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Vehicle Management</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .login-box {
            background: white;
            padding: 40px;
            border-radius: 10px;
            width: 350px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.3);
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
        <h2>🔐 SLIIT vehicle park</h2>

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