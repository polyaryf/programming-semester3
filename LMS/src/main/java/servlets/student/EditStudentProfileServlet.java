package servlets.student;

import dto.StudentDto;
import dto.UpdatedStudent;
import exception.FileSizeException;
import model.FileInfo;
import services.FileService;
import services.student.StudentMapper;
import services.student.StudentService;

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

@WebServlet("/profile-student-edit")
@MultipartConfig(
        fileSizeThreshold = 1024 * 20,  // 20 KB
        maxFileSize = 1024 * 300,       // 300 KB
        maxRequestSize = 1024 * 1024    // 1 MB
)
public class EditStudentProfileServlet extends HttpServlet {
    StudentService studentService;
    StudentMapper studentMapper;
    FileService fileService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        studentService = (StudentService) context.getAttribute("studentService");
        studentMapper = (StudentMapper) context.getAttribute("studentMapper");
        fileService = (FileService) context.getAttribute("fileService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/editStudentProfile.ftl").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UpdatedStudent updatedStudent = UpdatedStudent.builder()
                .tgUsername(request.getParameter("tgUsername"))
                .build();

        Part photoFile = request.getPart("photoFile");
        FileInfo fileInfo;
        StudentDto studentDto = (StudentDto) request.getSession(false).getAttribute("user");

        try {
            fileInfo = fileService.saveFileToStorage(photoFile.getInputStream(),
                    photoFile.getSubmittedFileName(),
                    photoFile.getContentType(),
                    photoFile.getSize());

            updatedStudent.setPhotoId(fileInfo.getId());
            StudentDto updatedStudentProfile = studentService.saveChangesFor(studentDto, updatedStudent);

            request.getSession(false).setAttribute("user", updatedStudentProfile);
        } catch (FileSizeException e) {
            response.setStatus(400);
            response.getWriter().println(e.getMessage());
        }
        response.sendRedirect("/profile");
    }
}
