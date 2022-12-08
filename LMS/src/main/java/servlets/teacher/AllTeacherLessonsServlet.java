package servlets.teacher;

import dto.LessonDto;
import dto.TeacherDto;
import services.lesson.LessonService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/my-lessons")
public class AllTeacherLessonsServlet extends HttpServlet {
    private LessonService lessonService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        lessonService = (LessonService) servletContext.getAttribute("lessonService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TeacherDto teacherDto = (TeacherDto) request.getSession(false).getAttribute("user");
        List<LessonDto> lessonDtoList = lessonService.getLessonsOf(teacherDto);

        request.setAttribute("lessonDtoList", lessonDtoList);
        request.getRequestDispatcher("/allTeacherLessons.ftl").forward(request, response);
    }

}
