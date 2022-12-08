package servlets.teacher;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.TeacherDto;
import dto.UpdatedTeacher;
import exception.FileSizeException;
import model.FileInfo;
import services.FileService;
import services.teacher.TeacherMapper;
import services.teacher.TeacherService;

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


@WebServlet("/profile-teacher-edit")
@MultipartConfig(
        fileSizeThreshold = 1024 * 20,  // 20 KB
        maxFileSize = 1024 * 300,       // 300 KB
        maxRequestSize = 1024 * 1024    // 1 MB
)
public class EditTeacherProfileServlet extends HttpServlet {
    TeacherService teacherService;
    TeacherMapper teacherMapper;
    FileService fileService;
    ObjectMapper objectMapper;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        teacherService = (TeacherService) context.getAttribute("teacherService");
        teacherMapper = (TeacherMapper) context.getAttribute("teacherMapper");
        fileService = (FileService) context.getAttribute("fileService");
        objectMapper = (ObjectMapper) context.getAttribute("objectMapper");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/editTeacherProfile.html").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UpdatedTeacher updatedTeacher = UpdatedTeacher.builder()
                .description(request.getParameter("description"))
                .workExperience(Integer.valueOf(request.getParameter("experience")))
                .tgUsername(request.getParameter("tgUsername"))
                .build();

        Part photoFile = request.getPart("photoFile");
        FileInfo fileInfo;

        TeacherDto teacherDto = (TeacherDto) request.getSession(false).getAttribute("user");
        try {
            fileInfo = fileService.saveFileToStorage(photoFile.getInputStream(),
                    photoFile.getSubmittedFileName(),
                    photoFile.getContentType(),
                    photoFile.getSize());

            updatedTeacher.setPhotoId(fileInfo.getId());
            TeacherDto updatedTeacherProfile = teacherService.saveChangesFor(teacherDto, updatedTeacher);

            request.getSession(false).setAttribute("user", updatedTeacherProfile);
        } catch (FileSizeException e) {
            response.setStatus(400);
            response.getWriter().println(e.getMessage());
        }
        response.sendRedirect("/profile");

    }
}
