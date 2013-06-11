package com.zenika.resanet.ui;

import com.zenika.resanet.dao.ConnectionManager;
import com.zenika.resanet.exception.OperatorNotFoundException;
import com.zenika.resanet.service.AuthenticationService;
import com.zenika.resanet.service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        String login = httpServletRequest.getParameter("login");
        String password = httpServletRequest.getParameter("password");
        
        Connection cx = ConnectionManager.getInstance().getConnection();
        AuthenticationService authenticationService = ServiceFactory
                .getInstance().getAuthenticationService();

        try {
            authenticationService.authenticate(cx,login, password);
        } catch (OperatorNotFoundException e) {
            throw new ServletException(e);
        }

        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("login", login);

        RequestDispatcher rd = httpServletRequest.getRequestDispatcher("/home"
                + ".jsp");
        rd.forward(httpServletRequest, httpServletResponse);
    }
}
