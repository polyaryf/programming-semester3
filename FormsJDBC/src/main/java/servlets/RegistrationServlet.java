package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.RegistrationForm;
import dto.UserDto;
import services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    UsersService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        usersService = (UsersService) context.getAttribute("usersService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/registration.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        RegistrationForm registrationForm = objectMapper.readValue(request.getReader(), RegistrationForm.class);
        UserDto profile = usersService.register(registrationForm);
        response.setContentType("application/json");
        objectMapper.writeValue(response.getWriter(), profile);
        HttpSession session = request.getSession(true);
        session.setAttribute("profile", profile);
    }
}
