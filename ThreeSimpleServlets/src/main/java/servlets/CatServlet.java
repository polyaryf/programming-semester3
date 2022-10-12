package servlets;

import model.Cat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/pet/cat")
public class CatServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cat cat = new Cat("Funtic","grey");
        request.setAttribute("catForJsp", cat);
        request.getRequestDispatcher("/templates/cat.jsp").forward(request, response);
    }
}
