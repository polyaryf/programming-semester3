package servlets;

import model.Status;
import dto.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession(false).getAttribute("user");
        Status status = user.getStatus();
        if(status.equals(Status.STUDENT)) {
            request.getRequestDispatcher("/profile-student").forward(request, response);
        } else {
            request.getRequestDispatcher("/profile-teacher").forward(request, response);
        }
    }
}
