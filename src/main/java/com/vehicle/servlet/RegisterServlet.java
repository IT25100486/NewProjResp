// 4. RegisterServlet.java
package com.vehicle.servlet;

import com.vehicle.dao.UserFileDAO;
import com.vehicle.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserFileDAO userDAO = new UserFileDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String vehicleNo = request.getParameter("vehicleNo");
        String telNo = request.getParameter("telNo");
        String role = request.getParameter("role");
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        User user = new User(0, name, vehicleNo, telNo, role, userId, password);
        boolean success = userDAO.registerUser(user);

        if (success) {
            response.sendRedirect("index.jsp?msg=Registration Successful! Please Login.");
        } else {
            request.setAttribute("error", "Registration Failed. User ID already exists!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}