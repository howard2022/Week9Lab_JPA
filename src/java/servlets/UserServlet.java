package servlets;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Role;
import models.User;
import services.RoleService;
import services.UserService;

public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserService us = new UserService();

        String action = request.getParameter("action");

        try {
            if (action != null && action.equals("edit")) {
                String email = request.getParameter("email");
                User user = us.get(email);
                request.setAttribute("selectedUser", user);

            } else if (action != null && action.equals("delete")) {
                String email = request.getParameter("email");
                us.delete(email);
                List<User> users = us.getAll();

                if (users.isEmpty()) {
                    request.setAttribute("Message", "No users found. Please add a user.");
                }
            }

            List<User> users = us.getAll();
            request.setAttribute("allUsers", users);

        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserService us = new UserService();
        String action = request.getParameter("action");

        String email = request.getParameter("email");
        String firstname = request.getParameter("firstName");
        String lastname = request.getParameter("lastName");
        String password = request.getParameter("password");
        int roleID = Integer.parseInt(request.getParameter("role"));

        try {
            switch (action) {
                case "add":
                    us.insert(email, firstname, lastname, password, roleID);
                    break;

                case "update":
                    us.update(email, firstname, lastname, password, roleID);
                    

                case "cancel":
                    getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
            }

        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }
        try {
            List<User> users = us.getAll();
            request.setAttribute("allUsers", users);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        getServletContext()
                .getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

}
