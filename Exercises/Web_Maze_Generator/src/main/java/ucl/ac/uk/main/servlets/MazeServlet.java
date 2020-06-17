package ucl.ac.uk.main.servlets;

import ucl.ac.uk.main.Classes.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/buildmaze.html")
public class MazeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        Maze obj = new Maze();
        ArrayList<ArrayList<Cell>> maze = obj.maze(10, 10);
        // Each time you level up increase id by 1. To calc new dimensions just do 10 + id * 2.
        // Once 10 + id * 2 == 40 you reach last level.
        request.setAttribute("id", 0);
        request.setAttribute("maze", maze);

        ServletContext ctx = getServletContext();
        RequestDispatcher dispatch = ctx.getRequestDispatcher("/maze.jsp");
        dispatch.forward(request, response);
    }
}
