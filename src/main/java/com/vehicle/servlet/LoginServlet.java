// 3. LoginServlet.java
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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserFileDAO userDAO = new UserFileDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        User user = userDAO.login(userId, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("userId", user.getId());
            response.sendRedirect("profile.jsp");
        } else {
            request.setAttribute("error", "Invalid User ID or Password");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}