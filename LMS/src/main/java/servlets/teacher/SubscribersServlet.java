package servlets.teacher;

import dto.Subscriber;
import dto.TeacherDto;
import services.student.StudentService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/subscribers")
public class SubscribersServlet extends HttpServlet {
    StudentService studentService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        studentService = (StudentService) servletContext.getAttribute("studentService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TeacherDto teacherProfile = (TeacherDto) request.getSession(false).getAttribute("user");
        List<Subscriber> subscribers = studentService.getSubscribers(teacherProfile);
        request.setAttribute("subscribers", subscribers);
        request.getRequestDispatcher("/subscribers.ftl").forward(request, response);
    }
}
