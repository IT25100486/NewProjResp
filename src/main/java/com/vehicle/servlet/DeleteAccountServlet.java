package com.vehicle.servlet;

import com.vehicle.dao.UserFileDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deleteAccount")
public class DeleteAccountServlet extends HttpServlet {
    private UserFileDAO userDAO = new UserFileDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId != null) {
            userDAO.deleteUser(userId);
            session.invalidate();
            response.sendRedirect("index.jsp?msg=Account deleted successfully");
        } else {
            response.sendRedirect("index.jsp");
        }
    }
}