package servlets.lesson;

import dto.FavouriteLesson;
import dto.LessonDto;
import dto.StudentDto;
import manager.RequestManager;
import services.lesson.LessonMapper;
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

@WebServlet("/lessons")
public class LessonsServlet extends HttpServlet {
    LessonService lessonService;
    LessonMapper lessonMapper;
    RequestManager requestManager;


    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        lessonService = (LessonService) servletContext.getAttribute("lessonService");
        lessonMapper = (LessonMapper) servletContext.getAttribute("lessonMapper");
        requestManager = (RequestManager) servletContext.getAttribute("requestManager");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<LessonDto> lessonDtoList = lessonService.getAllLessons();
        request.setAttribute("lessonDtoList", lessonDtoList);
        request.getRequestDispatcher("/lessons.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StudentDto studentDto = (StudentDto) request.getSession(false).getAttribute("user");
        Integer lessonId = Integer.valueOf(requestManager.getBody(request));
        Integer studentId = studentDto.getId();

        FavouriteLesson favouriteLesson = lessonMapper.toFavouriteLessonFrom(lessonId, studentId);
        Boolean flag = lessonService.isFavouriteLessonAdded(lessonId, studentId);

        if (flag == false) {
            lessonService.addLikedLesson(favouriteLesson);
        }
    }
}
