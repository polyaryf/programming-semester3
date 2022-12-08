package context;

import dao.UsersRepository;
import dao.impl.UsersRepositoryTempImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppContext implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        UsersRepository usersRepository = new UsersRepositoryTempImpl();
        context.setAttribute("usersRepository", usersRepository);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {}
}
