package servlets.teacher;

import dto.TeacherDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/profile-teacher")
public class TeacherProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        TeacherDto teacherProfile = (TeacherDto) session.getAttribute("user");
        request.setAttribute("teacherProfile", teacherProfile);
        request.getRequestDispatcher("/teacherProfile.ftl").forward(request, response);
    }
}
