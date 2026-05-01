<!-- 8. register.jsp - Registration Page -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register - Vehicle Management</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }
        .register-container {
            background: white;
            border-radius: 20px;
            box-shadow: 0 20px 40px rgba(0,0,0,0.2);
            padding: 40px;
            width: 500px;
            animation: fadeIn 0.5s ease;
        }
        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(-20px); }
            to { opacity: 1; transform: translateY(0); }
        }
        h2 { text-align: center; color: #333; margin-bottom: 30px; font-size: 28px; }
        .form-group { margin-bottom: 18px; }
        label { display: block; margin-bottom: 8px; color: #555; font-weight: 500; }
        input, select {
            width: 100%;
            padding: 12px;
            border: 2px solid #e0e0e0;
            border-radius: 10px;
            font-size: 14px;
            transition: all 0.3s;
        }
        input:focus, select:focus {
            outline: none;
            border-color: #667eea;
            box-shadow: 0 0 10px rgba(102,126,234,0.3);
        }
        .radio-group { display: flex; gap: 20px; margin-top: 8px; }
        .radio-group label { display: flex; align-items: center; gap: 8px; font-weight: normal; cursor: pointer; }
        .radio-group input { width: auto; }
        button {
            width: 100%;
            padding: 12px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            border: none;
            border-radius: 10px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: transform 0.2s;
            margin-top: 10px;
        }
        button:hover { transform: translateY(-2px); box-shadow: 0 5px 20px rgba(102,126,234,0.4); }
        .login-link { text-align: center; margin-top: 20px; }
        .login-link a { color: #667eea; text-decoration: none; font-weight: bold; }
        .login-link a:hover { text-decoration: underline; }
        .error { background: #fee; color: #c33; padding: 10px; border-radius: 10px; margin-bottom: 20px; text-align: center; }
    </style>
</head>
<body>
    <div class="register-container">
        <h2>Registration Page</h2>
        <% if(request.getAttribute("error") != null) { %>
            <div class="error"><%= request.getAttribute("error") %></div>
        <% } %>
        <form action="register" method="post">
            <div class="form-group">
                <label>Name</label>
                <input type="text" name="name" required placeholder="Enter your full name">
            </div>
            <div class="form-group">
                <label>Vehicle No</label>
                <input type="text" name="vehicleNo" placeholder="Enter vehicle number (optional)">
            </div>
            <div class="form-group">
                <label>Tel. No</label>
                <input type="tel" name="telNo" required placeholder="Enter your phone number">
            </div>
            <div class="form-group">
                <label>Role</label>
                <div class="radio-group">
                    <label><input type="radio" name="role" value="student" required> Student</label>
                    <label><input type="radio" name="role" value="staff" required> Staff</label>
                </div>
            </div>
            <div class="form-group">
                <label>User ID</label>
                <input type="text" name="userId" required placeholder="Create a user ID">
            </div>
            <div class="form-group">
                <label>Password</label>
                <input type="password" name="password" required placeholder="Create a password">
            </div>
            <button type="submit">Register</button>
        </form>
        <div class="login-link">
            <a href="index.jsp">Already have an account? Login</a>
        </div>
    </div>
</body>
</html>