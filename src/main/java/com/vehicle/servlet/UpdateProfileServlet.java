package com.vehicle.servlet;

import com.vehicle.dao.UserFileDAO;
import com.vehicle.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/updateProfile")
public class UpdateProfileServlet extends HttpServlet {
    private UserFileDAO userDAO = new UserFileDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");

        if (currentUser != null) {
            String name = request.getParameter("name");
            String vehicleNo = request.getParameter("vehicleNo");
            String telNo = request.getParameter("telNo");
            String role = request.getParameter("role");
            String password = request.getParameter("password");

            currentUser.setName(name);
            currentUser.setVehicleNo(vehicleNo);
            currentUser.setTelNo(telNo);
            currentUser.setRole(role);
            currentUser.setPassword(password);

            boolean updated = userDAO.updateUser(currentUser);
            if (updated) {
                session.setAttribute("user", currentUser);
                session.setAttribute("message", "Profile updated successfully!");
            } else {
                session.setAttribute("error", "Update failed!");
            }
        }
        response.sendRedirect("profile.jsp");
    }
}