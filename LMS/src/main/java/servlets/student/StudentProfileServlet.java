package servlets.student;

import dto.StudentDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/profile-student")
public class StudentProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        StudentDto studentProfile = (StudentDto) session.getAttribute("user");
        request.setAttribute("studentProfile", studentProfile);
        request.getRequestDispatcher("/studentProfile.ftl").forward(request, response);
    }
}
