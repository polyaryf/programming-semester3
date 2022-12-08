package servlets;

import exception.NotFoundException;
import model.FileInfo;
import services.FileService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/files/*")
public class FileDownloadServlet extends HttpServlet {
    private FileService filesService;

    @Override
    public void init(ServletConfig config) {
        this.filesService = (FileService) config.getServletContext().getAttribute("fileService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer fileId;
        try {
            String fileIdString = request.getRequestURI().substring(7);
            fileId = Integer.parseInt(fileIdString);
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            response.setStatus(400);
            response.getWriter().println("Wrong request");
            return;
        }

        try {
            FileInfo fileInfo = filesService.getFileInfo(fileId);
            response.setContentType(fileInfo.getType());
            response.setContentLength(fileInfo.getSize().intValue());
            response.setHeader("Content-Disposition", "filename=\"" + fileInfo.getOriginalFileName() + "\"");
            filesService.readFileFromStorage(fileId, response.getOutputStream());
            response.flushBuffer();
        } catch (NotFoundException e) {
            response.setStatus(404);
            response.getWriter().println(e.getMessage());
        }
    }

}

