package servlets.teacher;

import dto.CreatedLesson;
import dto.LessonDto;
import dto.TeacherDto;
import exception.FileSizeException;
import model.FileInfo;
import services.FileService;
import services.lesson.LessonService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/create-lesson")
@MultipartConfig(
        fileSizeThreshold = 1024 * 10,  // 10 KB
        maxFileSize = 1024 * 300,       // 300 KB
        maxRequestSize = 1024 * 1024    // 1 MB
)
public class CreateLessonServlet extends HttpServlet {
    private FileService fileService;
    private LessonService lessonService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        fileService = (FileService) servletContext.getAttribute("fileService");
        lessonService = (LessonService) servletContext.getAttribute("lessonService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/createLessonPage.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CreatedLesson createdLesson = CreatedLesson.builder()
                .title(request.getParameter("title"))
                .description(request.getParameter("description"))
                .videoLink(request.getParameter("videoLink"))
                .build();

        Part  documentFile = request.getPart("documentFile");
        FileInfo fileInfo;
        TeacherDto teacherDto = (TeacherDto) request.getSession(false).getAttribute("user");
        try {
            fileInfo = fileService. saveFileToStorage(documentFile.getInputStream(),
                    documentFile.getSubmittedFileName(),
                    documentFile.getContentType(),
                    documentFile.getSize());

            lessonService.createLesson(createdLesson,fileInfo, teacherDto);
            response.sendRedirect("/my-lessons");
        } catch (FileSizeException e) {
            response.setStatus(400);
            response.getWriter().println(e.getMessage());
        }

    }
}

