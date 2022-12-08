package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.SignUpForm;
import dto.StudentDto;
import dto.TeacherDto;
import model.Status;
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

@WebServlet("/sign-up")
public class SignUpServlet extends HttpServlet {
    private StudentService studentService;
    private TeacherService teacherService;
    private ObjectMapper objectMapper;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        studentService = (StudentService) context.getAttribute("studentService");
        teacherService = (TeacherService) context.getAttribute("teacherService");
        objectMapper = (ObjectMapper) context.getAttribute("objectMapper");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/static/html/access.html").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        SignUpForm signUpForm = objectMapper.readValue(request.getReader(), SignUpForm.class);
        if(signUpForm.getStatus().equals(Status.STUDENT)) {

            HttpSession session = request.getSession(true);
            StudentDto studentProfile = studentService.register(signUpForm);

            session.setAttribute("user", studentProfile);

        } else {
            HttpSession session = request.getSession(true);
            TeacherDto teacherProfile = teacherService.register(signUpForm);

            session.setAttribute("user", teacherProfile);

        }
    }
}
