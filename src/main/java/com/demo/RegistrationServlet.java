package com.demo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String address = request.getParameter("address");
        String password = request.getParameter("password");

        String usernamePattern = "^([A-Z]{1}[a-zA-Z]{2,})$";
        String passwordShouldContainEightCharacterAndAtLeastOneUppercase = "^(?=.*[A-Z])[A-Za-z0-9]{8,}$";
        String passwordShouldContainAtLeastOneNumericValue = "^(?=.*[A-Z])(?=.*[0-9])[A-Za-z0-9]{8,}$";
        String passwordShouldContainExactlyOneCharacter = "^(?=.?[A-Z])(?=[^@|#|$|%|&]*[@|#|$|%|&][^@|#|$|%|&]*$)(?=.*[0-9])[A-Za-z0-9@#$%^&]{8,}$";


        if (!(Pattern.matches(usernamePattern, username)) || !(Pattern.matches(passwordShouldContainExactlyOneCharacter, password))) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/registration.html");
            PrintWriter out = response.getWriter();
            if (!(Pattern.matches(usernamePattern, username)))
                out.println("<font color=red>Name Starts With Caps And Has Minimum Three Character.</font>");
            else if (!(Pattern.matches(passwordShouldContainEightCharacterAndAtLeastOneUppercase, password)))
                out.println("<font color=red>Password Should Have Minimum Eight Character And At Least One Uppercase.</font>");
            else if (!(Pattern.matches(passwordShouldContainAtLeastOneNumericValue, password)))
                out.println("<font color=red>Password Should Have At Least One Numeric Value.</font>");
            else
                out.println("<font color=red>Password Should Have Exactly One Special Character.</font>");
            rd.include(request, response);
        } else {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("username", username);
            httpSession.setAttribute("password", password);
            request.getRequestDispatcher("/RegistrationSuccess.jsp").forward(request, response);
        }
    }
}
