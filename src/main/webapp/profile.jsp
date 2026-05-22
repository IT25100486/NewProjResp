<!-- 9. profile.jsp - Profile Page with full CRUD -->
<%@ page import="com.vehicle.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Profile - Vehicle Management</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            padding: 40px 20px;
        }
        .profile-container {
            max-width: 600px;
            margin: 0 auto;
            background: white;
            border-radius: 20px;
            box-shadow: 0 20px 40px rgba(0,0,0,0.2);
            overflow: hidden;
            animation: fadeIn 0.5s ease;
        }
        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(-20px); }
            to { opacity: 1; transform: translateY(0); }
        }
        .profile-header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 30px;
            text-align: center;
        }
        .profile-header h2 { font-size: 28px; margin-bottom: 5px; }
        .profile-header p { opacity: 0.9; }
        .profile-body { padding: 30px; }
        .form-group { margin-bottom: 20px; }
        label { display: block; margin-bottom: 8px; color: #555; font-weight: 500; }
        input {
            width: 100%;
            padding: 12px;
            border: 2px solid #e0e0e0;
            border-radius: 10px;
            font-size: 14px;
            transition: all 0.3s;
        }
        input:focus {
            outline: none;
            border-color: #667eea;
            box-shadow: 0 0 10px rgba(102,126,234,0.3);
        }
        .radio-group { display: flex; gap: 20px; margin-top: 8px; }
        .radio-group label { display: flex; align-items: center; gap: 8px; font-weight: normal; cursor: pointer; }
        .radio-group input { width: auto; }
        .button-group { display: flex; gap: 15px; margin-top: 20px; }
        .btn-save {
            flex: 1; padding: 12px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white; border: none; border-radius: 10px;
            font-size: 16px; font-weight: bold; cursor: pointer;
            transition: transform 0.2s;
        }
        .btn-cancel {
            flex: 1; padding: 12px;
            background: #f0f0f0; color: #666; border: none;
            border-radius: 10px; font-size: 16px; font-weight: bold;
            cursor: pointer; transition: all 0.2s;
        }
        .btn-delete {
            width: 100%; padding: 12px;
            background: #ff4757; color: white; border: none;
            border-radius: 10px; font-size: 16px; font-weight: bold;
            cursor: pointer; margin-top: 15px;
            transition: transform 0.2s;
        }
        .btn-save:hover, .btn-cancel:hover, .btn-delete:hover { transform: translateY(-2px); box-shadow: 0 5px 20px rgba(0,0,0,0.2); }
        .btn-cancel:hover { background: #e0e0e0; }
        .btn-delete:hover { background: #e04350; }
        .message { padding: 12px; border-radius: 10px; margin-bottom: 20px; text-align: center; }
        .message.success { background: #d4edda; color: #155724; border: 1px solid #c3e6cb; }
        .message.error { background: #f8d7da; color: #721c24; border: 1px solid #f5c6cb; }
        .logout-btn { display: inline-block; margin-top: 20px; text-align: center; color: #667eea; text-decoration: none; font-weight: bold; }
        .logout-btn:hover { text-decoration: underline; }
        hr { margin: 20px 0; border: none; border-top: 1px solid #eee; }
        .readonly-field { background: #f8f9fa; color: #666; cursor: not-allowed; }
    </style>
    <script>
        function confirmDelete() {
            return confirm(' Are you sure you want to delete your account?\nThis action cannot be undone!');
        }
        function cancelEdit() { window.location.href = 'profile.jsp'; }
    </script>
</head>
<body>
    <div class="profile-container">
        <div class="profile-header">
            <h2> Profile</h2>
            <p>Welcome, <%= user.getName() %>! (<%= user.getRole() %>)</p>
        </div>
        <div class="profile-body">
            <% if(session.getAttribute("message") != null) { %>
                <div class="message success"> <%= session.getAttribute("message") %></div>
                <% session.removeAttribute("message"); %>
            <% } %>
            <% if(session.getAttribute("error") != null) { %>
                <div class="message error"> <%= session.getAttribute("error") %></div>
                <% session.removeAttribute("error"); %>
            <% } %>

            <form action="updateProfile" method="post">
                <div class="form-group">
                    <label>Name</label>
                    <input type="text" name="name" value="<%= user.getName() %>" required>
                </div>
                <div class="form-group">
                    <label>Vehicle No</label>
                    <input type="text" name="vehicleNo" value="<%= user.getVehicleNo() != null && !user.getVehicleNo().isEmpty() ? user.getVehicleNo() : "" %>" placeholder="Enter vehicle number">
                </div>
                <div class="form-group">
                    <label>Tel. No</label>
                    <input type="tel" name="telNo" value="<%= user.getTelNo() %>" required>
                </div>
                <div class="form-group">
                    <label>Role</label>
                    <div class="radio-group">
                        <label><input type="radio" name="role" value="student" <%= "student".equals(user.getRole()) ? "checked" : "" %>> Student</label>
                        <label><input type="radio" name="role" value="staff" <%= "staff".equals(user.getRole()) ? "checked" : "" %>> Staff</label>
                    </div>
                </div>
                <div class="form-group">
                    <label>User ID</label>
                    <input type="text" name="userId" value="<%= user.getUserId() %>" class="readonly-field" readonly>
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input type="password" name="password" value="<%= user.getPassword() %>" required>
                </div>
                <div class="button-group">
                    <button type="submit" class="btn-save"> Save</button>
                    <button type="button" class="btn-cancel" onclick="cancelEdit()"> Cancel</button>
                </div>
            </form>

            <hr>

            <form action="deleteAccount" method="post" onsubmit="return confirmDelete()">
                <button type="submit" class="btn-delete"> Delete Account</button>
            </form>

            <div style="text-align: center;">
                <a href="logout.jsp" class="logout-btn"> Logout</a>
            </div>
        </div>
    </div>
</body>
</html>