package com.demo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

@WebServlet(
        description = "Login Servlet Testing",
        urlPatterns = {"/LoginServlet"},
        initParams = {
                @WebInitParam(name = "user", value = "Abhishek"),
                @WebInitParam(name = "password", value = "Abhishek1@")
        }
)

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");

        String userID = getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");

        String usernamePattern = "^([A-Z]{1}[a-zA-Z]{2,})$";
        String passwordShouldContainEightCharacterAndAtLeastOneUppercase = "^(?=.*[A-Z])[A-Za-z0-9]{8,}$";
        String passwordShouldContainAtLeastOneNumericValue = "^(?=.*[A-Z])(?=.*[0-9])[A-Za-z0-9]{8,}$";
        String passwordShouldContainExactlyOneCharacter = "^(?=.?[A-Z])(?=[^@|#|$|%|&]*[@|#|$|%|&][^@|#|$|%|&]*$)(?=.*[0-9])[A-Za-z0-9@#$%^&]{8,}$";


        if (!(Pattern.matches(usernamePattern, user)) || !(Pattern.matches(passwordShouldContainExactlyOneCharacter, pwd))) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            if (!(Pattern.matches(usernamePattern, user)))
                out.println("<font color=red>Name Starts With Caps And Has Minimum Three Character.</font>");
            else if (!(Pattern.matches(passwordShouldContainEightCharacterAndAtLeastOneUppercase, pwd)))
                out.println("<font color=red>Password Should Have Minimum Eight Character And At Least One Uppercase.</font>");
            else if (!(Pattern.matches(passwordShouldContainAtLeastOneNumericValue, pwd)))
                out.println("<font color=red>Password Should Have At Least One Numeric Value.</font>");
            else
                out.println("<font color=red>Password Should Have Exactly One Special Character.</font>");
            rd.include(request, response);
        } else {
            if (userID.equals(user) && password.equals(pwd)) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
            } else {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
                PrintWriter out = response.getWriter();
                out.println("<font color=red>Either user name or password is wrong.</font>");
                rd.include(request, response);
            }
        }
    }
}
