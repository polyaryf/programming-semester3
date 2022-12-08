package context;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.FileRepository;
import dao.LessonRepository;
import dao.StudentRepository;
import dao.TeacherRepository;
import dao.impl.FileRepositoryImpl;
import dao.impl.LessonRepositoryImpl;
import dao.impl.StudentRepositoryImpl;
import dao.impl.TeacherRepositoryImpl;
import manager.RequestManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import services.FileService;
import services.HashService;
import services.impl.FileServiceImpl;
import services.impl.HashServiceImpl;
import services.lesson.LessonMapper;
import services.lesson.LessonService;
import services.lesson.impl.LessonMapperImpl;
import services.lesson.impl.LessonServiceImpl;
import services.student.StudentMapper;
import services.student.StudentService;
import services.student.impl.StudentMapperImpl;
import services.student.impl.StudentServiceImpl;
import services.teacher.TeacherMapper;
import services.teacher.TeacherService;
import services.teacher.impl.TeacherMapperImpl;
import services.teacher.impl.TeacherServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContext implements ServletContextListener {
    private static final String DB_USERNAME = "polinom";
    private static final String DB_PASSWORD = "123123";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/semester_task_1";
    private static final String DB_DRIVER = "org.postgresql.Driver";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUrl(DB_URL);

        StudentRepository studentRepository = new StudentRepositoryImpl(dataSource);
        servletContext.setAttribute("studentRepository", studentRepository);

        TeacherRepository teacherRepository = new TeacherRepositoryImpl(dataSource);
        servletContext.setAttribute("teacherRepository", teacherRepository);

        LessonRepository lessonRepository = new LessonRepositoryImpl(dataSource);
        servletContext.setAttribute("lessonRepository", lessonRepository);

        FileRepository fileRepository = new FileRepositoryImpl(dataSource);
        servletContext.setAttribute("fileRepository", fileRepository);

        HashService hashService = new HashServiceImpl();
        servletContext.setAttribute("hashService", hashService);

        TeacherMapper teacherMapper = new TeacherMapperImpl(hashService, teacherRepository);
        servletContext.setAttribute("teacherMapper", teacherMapper);

        StudentMapper studentMapper = new StudentMapperImpl(hashService);
        servletContext.setAttribute("studentMapper", studentMapper);

        LessonMapper lessonMapper = new LessonMapperImpl();
        servletContext.setAttribute("lessonMapper", lessonMapper);

        StudentService studentService = new StudentServiceImpl(studentRepository, studentMapper, hashService);
        servletContext.setAttribute("studentService", studentService);

        TeacherService teacherService = new TeacherServiceImpl(teacherRepository, teacherMapper, hashService);
        servletContext.setAttribute("teacherService", teacherService);

        LessonService lessonService = new LessonServiceImpl(lessonRepository, lessonMapper);
        servletContext.setAttribute("lessonService", lessonService);

        FileService fileService = new FileServiceImpl(fileRepository);
        servletContext.setAttribute("fileService", fileService);

        ObjectMapper objectMapper = new ObjectMapper();
        servletContext.setAttribute("objectMapper", objectMapper);

        RequestManager requestManager = new RequestManager();
        servletContext.setAttribute("requestManager", requestManager);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
