<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Destroy the session
    session.invalidate();

    // Redirect to login page with logout message
    response.sendRedirect("index.jsp?msg=You have been logged out successfully");
%>