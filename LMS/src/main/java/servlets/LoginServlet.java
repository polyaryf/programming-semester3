package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.ErrorDto;
import dto.LoginForm;
import dto.StudentDto;
import dto.TeacherDto;
import exception.IncorrectPasswordException;
import exception.NotFoundException;
import model.Status;
import services.HashService;
import services.student.StudentService;
import services.teacher.TeacherService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private StudentService studentService;
    private TeacherService teacherService;
    private ObjectMapper objectMapper;


    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        teacherService = (TeacherService) servletContext.getAttribute("teacherService");
        studentService = (StudentService) servletContext.getAttribute("studentService");
        objectMapper = (ObjectMapper) servletContext.getAttribute("objectMapper");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/static/html/access.html").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        LoginForm loginForm = LoginForm.builder()
                .email(request.getParameter("email"))
                .password(request.getParameter("password"))
                .status(Status.valueOf(request.getParameter("status")))
                .build();
        StudentDto studentProfile = null;
        TeacherDto teacherProfile = null;
        boolean flag = loginForm.getStatus().equals(Status.STUDENT);
        try{
            if(flag) {
                studentProfile = studentService.signIn(loginForm);
            } else {
                teacherProfile = teacherService.signIn(loginForm);
            }
        } catch (NotFoundException e) {
            response.setStatus(404);
            System.out.println("404 " + e.getMessage());
            objectMapper.writeValue(response.getWriter(), new ErrorDto(e.getMessage()));
            return;
        } catch (IncorrectPasswordException e) {
            response.setStatus(400);
            System.out.println("400 " + e.getMessage());
            objectMapper.writeValue(response.getWriter(), new ErrorDto(e.getMessage()));
            return;
        }
        HttpSession session = request.getSession(true);
        if(flag) {
            session.setAttribute("user",studentProfile);
        } else {
            session.setAttribute("user", teacherProfile);
        }
        response.sendRedirect("/profile");
    }
}
