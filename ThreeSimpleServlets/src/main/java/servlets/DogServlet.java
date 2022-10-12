package servlets;

import model.Dog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/pet/dog")
public class DogServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Dog doggy = new Dog("Muhtar", "black");
        request.setAttribute("dogForJsp", doggy);
        request.getRequestDispatcher("/templates/dog.jsp").forward(request,response);
    }
}
